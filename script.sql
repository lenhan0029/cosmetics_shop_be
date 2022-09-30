USE [master]
GO
/****** Object:  Database [cosmetics]    Script Date: 9/19/2022 7:15:23 PM ******/
CREATE DATABASE [cosmetics]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'cosmetics', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\cosmetics.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'cosmetics_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\cosmetics_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [cosmetics] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [cosmetics].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [cosmetics] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [cosmetics] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [cosmetics] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [cosmetics] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [cosmetics] SET ARITHABORT OFF 
GO
ALTER DATABASE [cosmetics] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [cosmetics] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [cosmetics] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [cosmetics] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [cosmetics] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [cosmetics] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [cosmetics] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [cosmetics] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [cosmetics] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [cosmetics] SET  DISABLE_BROKER 
GO
ALTER DATABASE [cosmetics] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [cosmetics] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [cosmetics] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [cosmetics] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [cosmetics] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [cosmetics] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [cosmetics] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [cosmetics] SET RECOVERY FULL 
GO
ALTER DATABASE [cosmetics] SET  MULTI_USER 
GO
ALTER DATABASE [cosmetics] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [cosmetics] SET DB_CHAINING OFF 
GO
ALTER DATABASE [cosmetics] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [cosmetics] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [cosmetics] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [cosmetics] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'cosmetics', N'ON'
GO
ALTER DATABASE [cosmetics] SET QUERY_STORE = OFF
GO
USE [cosmetics]
GO
/****** Object:  Table [dbo].[account]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[password] [varchar](255) NULL,
	[status] [bit] NULL,
	[username] [varchar](255) NULL,
	[id_role] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[brand]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[brand](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[id_account] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cart_detail]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cart_detail](
	[id_cart] [int] NOT NULL,
	[id_product] [int] NOT NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_cart] ASC,
	[id_product] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[category]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[delivery_information]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[delivery_information](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[address] [varchar](255) NULL,
	[is_default] [bit] NULL,
	[name] [varchar](255) NULL,
	[phone_number] [varchar](255) NULL,
	[id_account] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[order_detail]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order_detail](
	[id_order] [int] NOT NULL,
	[id_product] [int] NOT NULL,
	[price] [int] NULL,
	[quantity] [int] NULL,
	[id_orders] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id_order] ASC,
	[id_product] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orders]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orders](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[created_date] [date] NULL,
	[paid_status] [bit] NULL,
	[updated_date] [date] NULL,
	[id_account] [int] NULL,
	[id_delivery_information] [int] NULL,
	[id_status] [int] NULL,
	[id_vourcher] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[image] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[price] [int] NULL,
	[quantity_unusable] [int] NULL,
	[quantity_usable] [int] NULL,
	[id_brand] [int] NULL,
	[id_promotion] [int] NULL,
	[id_type] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[promotion]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[promotion](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[expired_date] [date] NULL,
	[image] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[percentage] [int] NULL,
	[started_date] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[role]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[role](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[status]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[status](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
	[priority] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[type]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[type](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](255) NULL,
	[id_category] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user_information]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user_information](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[address] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[first_name] [varchar](255) NULL,
	[last_name] [varchar](255) NULL,
	[phone_number] [varchar](255) NULL,
	[id_account] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[vourcher]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[vourcher](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](255) NULL,
	[expired_date] [date] NULL,
	[image] [varchar](255) NULL,
	[name] [varchar](255) NULL,
	[percentage] [int] NULL,
	[started_date] [date] NULL,
	[quantity] [int] NULL,
	[status] [bit] NULL,
	[condition] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[vourcher_detail]    Script Date: 9/19/2022 7:15:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[vourcher_detail](
	[id_account] [int] NOT NULL,
	[id_vourcher] [int] NOT NULL,
	[usages] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id_account] ASC,
	[id_vourcher] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD  CONSTRAINT [FKn2ojv1jm3miwie24w3mop7j1p] FOREIGN KEY([id_role])
REFERENCES [dbo].[role] ([id])
GO
ALTER TABLE [dbo].[account] CHECK CONSTRAINT [FKn2ojv1jm3miwie24w3mop7j1p]
GO
ALTER TABLE [dbo].[cart]  WITH CHECK ADD  CONSTRAINT [FKldb5k6mk67oudki8jgt7ri03h] FOREIGN KEY([id_account])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[cart] CHECK CONSTRAINT [FKldb5k6mk67oudki8jgt7ri03h]
GO
ALTER TABLE [dbo].[cart_detail]  WITH CHECK ADD  CONSTRAINT [FKh5pbblis7iw2kog1cponwqb9c] FOREIGN KEY([id_cart])
REFERENCES [dbo].[cart] ([id])
GO
ALTER TABLE [dbo].[cart_detail] CHECK CONSTRAINT [FKh5pbblis7iw2kog1cponwqb9c]
GO
ALTER TABLE [dbo].[cart_detail]  WITH CHECK ADD  CONSTRAINT [FKiuld7os3kvusagp22ahcnrs28] FOREIGN KEY([id_product])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[cart_detail] CHECK CONSTRAINT [FKiuld7os3kvusagp22ahcnrs28]
GO
ALTER TABLE [dbo].[delivery_information]  WITH CHECK ADD  CONSTRAINT [FK4p5jqck8wysjdry9tlu84kga1] FOREIGN KEY([id_account])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[delivery_information] CHECK CONSTRAINT [FK4p5jqck8wysjdry9tlu84kga1]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FKicrtfcntxfkyrnoaqh1croidl] FOREIGN KEY([id_product])
REFERENCES [dbo].[product] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FKicrtfcntxfkyrnoaqh1croidl]
GO
ALTER TABLE [dbo].[order_detail]  WITH CHECK ADD  CONSTRAINT [FKsta0q8hk1lt2vdu92u4e5vr4a] FOREIGN KEY([id_order])
REFERENCES [dbo].[orders] ([id])
GO
ALTER TABLE [dbo].[order_detail] CHECK CONSTRAINT [FKsta0q8hk1lt2vdu92u4e5vr4a]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK45hfoltf7ji2rvgg6adpev6hw] FOREIGN KEY([id_delivery_information])
REFERENCES [dbo].[delivery_information] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK45hfoltf7ji2rvgg6adpev6hw]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FK50wr4umllb3hn6sp5g11wsba0] FOREIGN KEY([id_vourcher])
REFERENCES [dbo].[vourcher] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FK50wr4umllb3hn6sp5g11wsba0]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FKaob19i9poiwj8cokp0u3unnhv] FOREIGN KEY([id_account])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FKaob19i9poiwj8cokp0u3unnhv]
GO
ALTER TABLE [dbo].[orders]  WITH CHECK ADD  CONSTRAINT [FKl1to63958wdnvadkm17uv9qfj] FOREIGN KEY([id_status])
REFERENCES [dbo].[status] ([id])
GO
ALTER TABLE [dbo].[orders] CHECK CONSTRAINT [FKl1to63958wdnvadkm17uv9qfj]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK55evcjddwhtr4pbaggns9nqc8] FOREIGN KEY([id_brand])
REFERENCES [dbo].[brand] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK55evcjddwhtr4pbaggns9nqc8]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FK5y91nfsk3v6ulgmlpr2wj2aa6] FOREIGN KEY([id_promotion])
REFERENCES [dbo].[promotion] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FK5y91nfsk3v6ulgmlpr2wj2aa6]
GO
ALTER TABLE [dbo].[product]  WITH CHECK ADD  CONSTRAINT [FKrhxj6mgk7eb68xd7ln6epbvk9] FOREIGN KEY([id_type])
REFERENCES [dbo].[type] ([id])
GO
ALTER TABLE [dbo].[product] CHECK CONSTRAINT [FKrhxj6mgk7eb68xd7ln6epbvk9]
GO
ALTER TABLE [dbo].[type]  WITH CHECK ADD  CONSTRAINT [FKfpkphn1gri9h6893j7him5222] FOREIGN KEY([id_category])
REFERENCES [dbo].[category] ([id])
GO
ALTER TABLE [dbo].[type] CHECK CONSTRAINT [FKfpkphn1gri9h6893j7him5222]
GO
ALTER TABLE [dbo].[user_information]  WITH CHECK ADD  CONSTRAINT [FK9jmjm5w3u25bl4ndc7fxxcysw] FOREIGN KEY([id_account])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[user_information] CHECK CONSTRAINT [FK9jmjm5w3u25bl4ndc7fxxcysw]
GO
ALTER TABLE [dbo].[vourcher_detail]  WITH CHECK ADD  CONSTRAINT [FKhtmm9qjbqhvnw2wugeqii2vtv] FOREIGN KEY([id_vourcher])
REFERENCES [dbo].[vourcher] ([id])
GO
ALTER TABLE [dbo].[vourcher_detail] CHECK CONSTRAINT [FKhtmm9qjbqhvnw2wugeqii2vtv]
GO
ALTER TABLE [dbo].[vourcher_detail]  WITH CHECK ADD  CONSTRAINT [FKrbg2c1fkud5tk4aavye2u8tqg] FOREIGN KEY([id_account])
REFERENCES [dbo].[account] ([id])
GO
ALTER TABLE [dbo].[vourcher_detail] CHECK CONSTRAINT [FKrbg2c1fkud5tk4aavye2u8tqg]
GO
USE [master]
GO
ALTER DATABASE [cosmetics] SET  READ_WRITE 
GO
