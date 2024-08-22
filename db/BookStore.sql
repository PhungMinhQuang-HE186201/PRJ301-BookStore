USE [master]
GO
/****** Object:  Database [BookStore]    Script Date: 6/3/2024 12:07:43 AM ******/
CREATE DATABASE [BookStore]

GO
USE [BookStore]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 6/3/2024 12:07:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 6/3/2024 12:07:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](150) NOT NULL,
	[image] [nvarchar](max) NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[description] [nvarchar](max) NULL,
	[category_id] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Category] ON 
GO
INSERT [dbo].[Category] ([id], [name]) VALUES (1, N'Shounen')
GO
INSERT [dbo].[Category] ([id], [name]) VALUES (2, N'Detective')
GO
INSERT [dbo].[Category] ([id], [name]) VALUES (3, N'Sports')
GO
INSERT [dbo].[Category] ([id], [name]) VALUES (4, N'Comedy')
GO
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (1, N'Dragon ball', N'http://dummyimage.com/233x176.png/cc0000/ffffff', 5, 30000, N'16th Floor', 1)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (2, N'Naruto', N'http://dummyimage.com/122x182.png/dddddd/000000', 1, 30000, N'7th Floor', 1)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (3, N'One piece', N'http://dummyimage.com/236x133.png/dddddd/000000', 7, 30000, N'PO Box 2694', 1)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (4, N'Kimetsu no yaiba', N'http://dummyimage.com/114x117.png/dddddd/000000', 9, 25000, N'PO Box 12831', 1)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (5, N'Jujutsu kaisen', N'http://dummyimage.com/250x183.png/5fa2dd/ffffff', 2, 25000, N'Apt 713', 1)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (6, N'My hero academia', N'http://dummyimage.com/121x227.png/dddddd/000000', 2, 25000, N'PO Box 85463', 1)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (7, N'One Punch Man', N'http://dummyimage.com/207x109.png/dddddd/000000', 2, 25000, N'Apt 1627', 1)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (8, N'Bleach', N'http://dummyimage.com/130x232.png/5fa2dd/ffffff', 3, 30000, N'Suite 53', 1)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (9, N'Conan', N'http://dummyimage.com/157x140.png/dddddd/000000', 2, 25000, N'11th Floor', 2)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (10, N'Death note', N'http://dummyimage.com/191x141.png/ff4444/ffffff', 9, 25000, N'11th Floor', 2)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (11, N'Kindaichi', N'http://dummyimage.com/177x238.png/5fa2dd/ffffff', 6, 25000, N'15th Floor', 2)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (12, N'Gosick', N'http://dummyimage.com/183x127.png/5fa2dd/ffffff', 10, 25000, N'15th Floor', 2)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (13, N'Tsubasa', N'http://dummyimage.com/221x156.png/cc0000/ffffff', 8, 25000, N'Apt 786', 3)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (14, N'Bluelock', N'http://dummyimage.com/153x102.png/ff4444/ffffff', 6, 25000, N'Apt 219', 3)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (15, N'Haikyuu', N'http://dummyimage.com/236x116.png/dddddd/000000', 9, 25000, N'Room 972', 3)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (16, N'Dorabase', N'http://dummyimage.com/222x173.png/dddddd/000000', 6, 25000, N'Room 1739', 3)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (17, N'Slam dun', N'http://dummyimage.com/242x233.png/dddddd/000000', 9, 25000, N'Room 347', 3)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (18, N'Spy x family', N'http://dummyimage.com/193x128.png/5fa2dd/ffffff', 5, 25000, N'PO Box 71853', 4)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (19, N'Beelzebub', N'http://dummyimage.com/108x249.png/cc0000/ffffff', 7, 25000, N'10th Floor', 4)
GO
INSERT [dbo].[Product] ([id], [name], [image], [quantity], [price], [description], [category_id]) VALUES (20, N'Doraemon', N'http://dummyimage.com/207x167.png/5fa2dd/ffffff', 2, 25000, N'Room 78', 4)
GO
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([category_id])
REFERENCES [dbo].[Category] ([id])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
USE [master]
GO
ALTER DATABASE [BookStore] SET  READ_WRITE 
GO
