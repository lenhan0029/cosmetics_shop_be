package com.cosmetics.cosmetics.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cosmetics.cosmetics.Exception.ResourceNotFoundException;
import com.cosmetics.cosmetics.Model.DTO.Request.CreateProduct;
import com.cosmetics.cosmetics.Model.DTO.Response.ProductDetail;
import com.cosmetics.cosmetics.Model.DTO.Response.ProductResponse;
import com.cosmetics.cosmetics.Model.DTO.Response.ResponseModel;
import com.cosmetics.cosmetics.Model.Entity.Brand;
import com.cosmetics.cosmetics.Model.Entity.Product;
import com.cosmetics.cosmetics.Model.Entity.Promotion;
import com.cosmetics.cosmetics.Model.Entity.Type;
import com.cosmetics.cosmetics.Repository.BrandRepository;
import com.cosmetics.cosmetics.Repository.CartDetailRepository;
import com.cosmetics.cosmetics.Repository.CartRepository;
import com.cosmetics.cosmetics.Repository.OrderDetailRepository;
import com.cosmetics.cosmetics.Repository.ProductRepository;
import com.cosmetics.cosmetics.Repository.PromotionRepository;
import com.cosmetics.cosmetics.Repository.TypeRepository;
import com.cosmetics.cosmetics.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;
	private final TypeRepository typeRepository;
	private final PromotionRepository promotionRepository;
	private final CartDetailRepository cartDetailRepository;
	private final OrderDetailRepository orderDetailRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository,
			TypeRepository typeRepository, PromotionRepository promotionRepository,
			CartDetailRepository cartDetailRepository, OrderDetailRepository orderDetailRepository) {
		super();
		this.productRepository = productRepository;
		this.brandRepository = brandRepository;
		this.typeRepository = typeRepository;
		this.promotionRepository = promotionRepository;
		this.cartDetailRepository = cartDetailRepository;
		this.orderDetailRepository = orderDetailRepository;
	}


	@Override
	public ResponseEntity<?> getProductBySearch(String name, String brand, String type, String category, int star,
			int from, int to, String sortType,int page) {
		int[] starArr = new int[]{0,0,0,0,0};
		if(star == 0) {
			for(int i = 0; i < 5; i++) {
				starArr[i] = i+1;
			}
		}else if(star < 9) {
			starArr[0] = star;
		}else {
			int temp;
			for(int i = 0; i < 5; i++) {
				starArr[i] = star%10;
				temp = star/10;
				star = temp;
				if(star < 0) {
					break;
				}
			}
		}
		
		
		if(to == 0) {
			to = productRepository.findMaxPrice();
		}
		Pageable newPage = createPage(page,sortType);

		Page<ProductResponse> pageProduct= this.productRepository.
				listProductBySearch(name.toLowerCase(),brand.toLowerCase(),type.toLowerCase(),category.toLowerCase(),
						newPage, starArr, from, to);
		if (pageProduct.hasContent())
		{
			return ResponseEntity.ok(new ResponseModel("Thành công",200,pageProduct));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Không tồn tại sản phẩm",200));
	}

	public Pageable createPage(int page, String sortType){

		String sType = sortType.toUpperCase();
		Sort sort;

		switch (sType){
			case "ASC":
				sort = Sort.by(Sort.Direction.ASC, "price");
				break;
			case "DESC":
				sort = Sort.by(Sort.Direction.DESC, "price");
				break;
			default:
				throw new ResourceNotFoundException("NO MODE SORT FOUND !");
		}

		return PageRequest.of(page,12,sort);

	}


	@Override
	public ResponseEntity<?> getByID(int id) {
		Optional<Product> otp = productRepository.findById(id);
		if(otp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Không tìm thấy sản phẩm",404));
		}
		Product product = otp.get();
		ProductDetail prdetail = new ProductDetail();
		prdetail.setId(product.getId());
		prdetail.setName(product.getName());
		prdetail.setImage(product.getImage());
		prdetail.setPrice(product.getPrice());
		prdetail.setQuantity(product.getQuantity());
		prdetail.setRate(product.getQuantity());
		prdetail.setDescription(product.getDescription());
		return ResponseEntity.ok().body(new ResponseModel("Thành công",200,prdetail));
	}


	@Override
	public ResponseEntity<?> createProduct(CreateProduct dto) {
		Optional<Brand> brand = brandRepository.findById(dto.getId_brand());
		Optional<Type> type = typeRepository.findById(dto.getId_type());
		Optional<Promotion> promotion = promotionRepository.findById(dto.getId_promotion());
		if(brand.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Thương hiệu không tồn tại",404));
		}
		if(type.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Loại sản phẩm không tồn tại",404));
		}
		Product product = new Product();
		product.setName(dto.getName());
		product.setImage(dto.getImage());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());
		product.setStar(dto.getRate());
		if(promotion.isPresent()) {
			product.setPromotion(promotion.get());
		}
		product.setDescription(dto.getDescription());
		product.setBrand(brand.get());
		product.setType(type.get());
		product.setStatus(dto.getStatus());
		Product newProduct = productRepository.save(product);
		ProductDetail pr = new ProductDetail(newProduct.getId(), newProduct.getName(), newProduct.getImage(),
				newProduct.getPrice(), newProduct.getStar(), newProduct.getQuantity(), newProduct.getDescription(),newProduct.getStatus());
		return ResponseEntity.ok().body(new ResponseModel("Thành công",200,pr));
	}

	@Override
	public ResponseEntity<?> updateProduct(int id, CreateProduct dto) {
		Optional<Product> otp = productRepository.findById(id);
		if(otp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy sản phẩm",404));
		}
		Optional<Brand> brand = brandRepository.findById(dto.getId_brand());
		Optional<Type> type = typeRepository.findById(dto.getId_type());
		Optional<Promotion> promotion = promotionRepository.findById(dto.getId_promotion());
		if(brand.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Thương hiệu không tồn tại",404));
		}
		if(type.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Loại sản phẩm không tồn tại",404));
		}
		
		Product oldProduct = otp.get();
		oldProduct.setImage(dto.getImage());
		if(promotion.isPresent()) {
			oldProduct.setPromotion(promotion.get());
		}
		oldProduct.setName(dto.getName());
		oldProduct.setPrice(dto.getPrice());
		oldProduct.setQuantity(dto.getQuantity());
		oldProduct.setStar(dto.getRate());;
		oldProduct.setDescription(dto.getDescription());
		oldProduct.setStatus(dto.getStatus());
		Product product = productRepository.save(oldProduct);
		ProductDetail prdetail = new ProductDetail();
		prdetail.setId(product.getId());
		prdetail.setName(product.getName());
		prdetail.setImage(product.getImage());
		prdetail.setPrice(product.getPrice());
		prdetail.setQuantity(product.getQuantity());
		prdetail.setRate(product.getQuantity());
		prdetail.setDescription(product.getDescription());
		prdetail.setStatus(product.getStatus());
		return ResponseEntity.ok().body(
				new ResponseModel("Sửa thành công",200,prdetail));
	}

	@Override
	public ResponseEntity<?> deleteProduct(int id) {
		Optional<Product> otp = productRepository.findById(id);
		if(otp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Không tìm thấy sản phẩm",404));
		}
		Product delProduct = otp.get();
		delProduct.setStatus(0);
		productRepository.save(delProduct);
		return ResponseEntity.ok().body(
				new ResponseModel("Xóa thành công",200));
	}
	
	
}
