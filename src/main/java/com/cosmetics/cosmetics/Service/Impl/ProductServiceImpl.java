package com.cosmetics.cosmetics.Service.Impl;

import java.util.Optional;
import java.util.stream.Stream;

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
import com.cosmetics.cosmetics.Model.Entity.Type;
import com.cosmetics.cosmetics.Repository.BrandRepository;
import com.cosmetics.cosmetics.Repository.CartDetailRepository;
import com.cosmetics.cosmetics.Repository.CategoryRepository;
import com.cosmetics.cosmetics.Repository.OrderDetailRepository;
import com.cosmetics.cosmetics.Repository.ProductRepository;
import com.cosmetics.cosmetics.Repository.TypeRepository;
import com.cosmetics.cosmetics.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;
	private final TypeRepository typeRepository;
	private final CategoryRepository categoryRepository;
	private final CartDetailRepository cartDetailRepository;
	private final OrderDetailRepository orderDetailRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, BrandRepository brandRepository,
			TypeRepository typeRepository, CategoryRepository categoryRepository,
			CartDetailRepository cartDetailRepository, OrderDetailRepository orderDetailRepository) {
		super();
		this.productRepository = productRepository;
		this.brandRepository = brandRepository;
		this.typeRepository = typeRepository;
		this.categoryRepository = categoryRepository;
		this.cartDetailRepository = cartDetailRepository;
		this.orderDetailRepository = orderDetailRepository;
	}


	@Override

	public ResponseEntity<?> getProductBySearch(String name, String brand, int type, Float star,
			int from, int to, String sortType,int page,int discount) {
		String[] arr_brand = brand.split("-");
		int[] listbrand = Stream.of(arr_brand).mapToInt(Integer::parseInt).toArray();
		if(to == 0) {
			to = productRepository.findMaxPrice();
		}
		Pageable newPage = createPage(page,sortType);
		Page<ProductResponse> pageProduct = this.productRepository.
				listProductByName(name.toLowerCase(),newPage, star, from, to,discount);
		if(!brand.equals("0") && type!=0 ) {
			pageProduct= this.productRepository.listProductBySearch(name.toLowerCase(),listbrand,type,
							newPage, star, from, to,discount);
		}else if(brand.equals("0") && type!=0) {
			pageProduct= this.productRepository.listProductByType(name.toLowerCase(),type,
							newPage, star, from, to,discount);
		}else if(!brand.equals("0") && type == 0) {
			pageProduct= this.productRepository.listProductByBrand(name.toLowerCase(),listbrand,
							newPage, star, from, to,discount);
		}
		if (pageProduct.hasContent())
		{
			return ResponseEntity.ok(new ResponseModel("th??nh c??ng",200,pageProduct));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("kh??ng t???n t???i s???n ph???m",200));
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
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Kh??ng t??m th???y s???n ph???m",404));
		}
		Product product = otp.get();
		ProductDetail prdetail = new ProductDetail();
		prdetail.setId(product.getId());
		prdetail.setName(product.getName());
		prdetail.setImage(product.getImage());
		prdetail.setPrice(product.getPrice());
		prdetail.setQuantity(product.getQuantity());
		prdetail.setRate(product.getStar());
		prdetail.setDescription(product.getDescription());
		prdetail.setBrand(product.getBrand().getName());
		prdetail.setCategory(product.getType().getCategory().getName());
		prdetail.setType(product.getType().getName());
		prdetail.setDiscount(product.getDiscount());
		return ResponseEntity.ok().body(new ResponseModel("Th??nh c??ng",200,prdetail));
	}


	@Override
	public ResponseEntity<?> createProduct(CreateProduct dto) {
		Optional<Brand> brand = brandRepository.findById(dto.getId_brand());
		Optional<Type> type = typeRepository.findById(dto.getId_type());
//		Optional<Promotion> promotion = promotionRepository.findById(dto.getId_promotion());
		if(brand.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Th????ng hi???u kh??ng t???n t???i",404));
		}
		if(type.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Lo???i s???n ph???m kh??ng t???n t???i",404));
		}
		Product product = new Product();
		product.setName(dto.getName());
		product.setImage(dto.getImage());
		product.setPrice(dto.getPrice());
		product.setQuantity(dto.getQuantity());
		product.setStar(dto.getRate());
		product.setDiscount(dto.getDiscount());
//		if(promotion.isPresent()) {
//			product.setPromotion(promotion.get());
//		}
		
		product.setDescription(dto.getDescription());
		product.setBrand(brand.get());
		product.setType(type.get());
		product.setStatus(dto.getStatus());
		Product newProduct = productRepository.save(product);
		ProductDetail pr = new ProductDetail(newProduct.getId(), newProduct.getName(), newProduct.getImage(),
				newProduct.getPrice(), newProduct.getStar(), newProduct.getQuantity(), newProduct.getDescription(),newProduct.getStatus(),
				newProduct.getBrand().getName(),newProduct.getType().getName(),newProduct.getType().getCategory().getName(),newProduct.getDiscount());
		return ResponseEntity.ok().body(new ResponseModel("Th??nh c??ng",200,pr));
	}

	@Override
	public ResponseEntity<?> updateProduct(int id, CreateProduct dto) {
		Optional<Product> otp = productRepository.findById(id);
		if(otp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Kh??ng t??m th???y s???n ph???m",404));
		}
		Optional<Brand> brand = brandRepository.findById(dto.getId_brand());
		Optional<Type> type = typeRepository.findById(dto.getId_type());
//		Optional<Promotion> promotion = promotionRepository.findById(dto.getId_promotion());
		if(brand.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Th????ng hi???u kh??ng t???n t???i",404));
		}
		if(type.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseModel("Lo???i s???n ph???m kh??ng t???n t???i",404));
		}
		
		Product oldProduct = otp.get();
		oldProduct.setImage(dto.getImage());
//		if(promotion.isPresent()) {
//			oldProduct.setPromotion(promotion.get());
//		}
		oldProduct.setDiscount(dto.getDiscount());
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
		prdetail.setRate(product.getStar());
		prdetail.setDescription(product.getDescription());
		prdetail.setStatus(product.getStatus());
		return ResponseEntity.ok().body(
				new ResponseModel("S???a th??nh c??ng",200,prdetail));
	}

	@Override
	public ResponseEntity<?> deleteProduct(int id) {
		Optional<Product> otp = productRepository.findById(id);
		if(otp.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseModel("Kh??ng t??m th???y s???n ph???m",404));
		}
		Product delProduct = otp.get();
		delProduct.setStatus(0);
		productRepository.save(delProduct);
		return ResponseEntity.ok().body(
				new ResponseModel("X??a th??nh c??ng",200));
	}
	
	
}
