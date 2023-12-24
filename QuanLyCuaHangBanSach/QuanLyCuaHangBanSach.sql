USE [QuanLyCuaHangBanSach]
GO
/****** Object:  Table [dbo].[ChiTietNhapSach]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietNhapSach](
	[MaPhieuNhap] [varchar](10) NOT NULL,
	[MaSach] [varchar](20) NOT NULL,
	[SoLuong] [int] NULL,
 CONSTRAINT [PK_CHITIETNHAPSACH] PRIMARY KEY CLUSTERED 
(
	[MaPhieuNhap] ASC,
	[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietPhieuBanHang]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuBanHang](
	[MaSach] [varchar](20) NOT NULL,
	[MaPhieuBan] [varchar](10) NOT NULL,
	[SoLuong] [int] NULL,
	[SoTien] [float] NULL,
 CONSTRAINT [PK_CHITIETPHIEUBANHANG] PRIMARY KEY CLUSTERED 
(
	[MaSach] ASC,
	[MaPhieuBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[MaChucVu] [varchar](5) NOT NULL,
	[TenChucVu] [nvarchar](30) NULL,
 CONSTRAINT [PK_CHUCVU] PRIMARY KEY CLUSTERED 
(
	[MaChucVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[SDT_KH] [numeric](10, 0) NOT NULL,
	[TenKhachHang] [nvarchar](50) NOT NULL,
	[SL_SachDaMua] [int] NULL,
	[GiamGia] [float] NULL,
 CONSTRAINT [PK_KHACHHANG] PRIMARY KEY CLUSTERED 
(
	[SDT_KH] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaCungCap]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaCungCap](
	[MaNCC] [varchar](6) NOT NULL,
	[TenNCC] [nvarchar](256) NOT NULL,
	[DiaChi] [nvarchar](256) NULL,
	[SDT] [numeric](10, 0) NULL,
	[Email] [varchar](128) NULL,
 CONSTRAINT [PK_NHACUNGCAP] PRIMARY KEY CLUSTERED 
(
	[MaNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNhanVien] [varchar](5) NOT NULL,
	[TenNV] [nvarchar](50) NULL,
	[GioiTinh] [bit] NULL,
	[NgaySinh] [date] NULL,
	[NgayVaoLam] [date] NULL,
	[DiaChi] [nvarchar](256) NULL,
	[SDT] [numeric](10, 0) NULL,
	[MaChucVu] [varchar](5) NULL,
	[TenDangNhap] [varchar](30) NULL,
	[HinhAnh] [varchar](50) NULL,
	[MatKhau] [varchar](30) NOT NULL,
	[LoaiTaiKhoan] [bit] NULL,
	[CCCD] [numeric](12, 0) NULL,
	[TrangThai] [bit] NULL,
 CONSTRAINT [PK_NHANVIEN] PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhaXuatBan]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhaXuatBan](
	[MaNXB] [varchar](6) NOT NULL,
	[TenNXB] [nvarchar](256) NOT NULL,
 CONSTRAINT [PK_NHAXUATBAN] PRIMARY KEY CLUSTERED 
(
	[MaNXB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuBanHang]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuBanHang](
	[MaPhieuBan] [varchar](10) NOT NULL,
	[SDT_KH] [numeric](10, 0) NOT NULL,
	[MaNhanVien] [varchar](5) NULL,
	[TongTien] [float] NULL,
	[NgayMua] [datetime] NULL,
	[GiamGia] [float] NULL,
	[SoTienPhaiTra] [float] NULL,
	[TrangThai] [bit] NULL,
 CONSTRAINT [PK_PHIEUBANHANG] PRIMARY KEY CLUSTERED 
(
	[MaPhieuBan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuNhapHang]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuNhapHang](
	[MaPhieuNhap] [varchar](10) NOT NULL,
	[MaNCC] [varchar](6) NOT NULL,
	[MaNhanVien] [varchar](5) NULL,
	[NgayNhap] [date] NULL,
 CONSTRAINT [PK_PHIEUNHAPHANG] PRIMARY KEY CLUSTERED 
(
	[MaPhieuNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Sach]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sach](
	[MaSach] [varchar](20) NOT NULL,
	[MaNXB] [varchar](6) NULL,
	[MaTheLoai] [varchar](5) NULL,
	[SerialSach] [varchar](13) NULL,
	[TenSach] [nvarchar](256) NOT NULL,
	[SoLuong] [int] NULL,
	[LuotMua] [int] NULL,
	[HinhAnh] [varchar](50) NULL,
	[GiaBan] [float] NULL,
	[TacGia] [nvarchar](512) NULL,
 CONSTRAINT [PK_SACH] PRIMARY KEY CLUSTERED 
(
	[MaSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TheLoai]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TheLoai](
	[MaTheLoai] [varchar](5) NOT NULL,
	[TenTheLoai] [nvarchar](128) NOT NULL,
 CONSTRAINT [PK_THELOAI] PRIMARY KEY CLUSTERED 
(
	[MaTheLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChiTietPhieuBanHang] ([MaSach], [MaPhieuBan], [SoLuong], [SoTien]) VALUES (N'NN01', N'PBH01', 7, 1057000)
INSERT [dbo].[ChiTietPhieuBanHang] ([MaSach], [MaPhieuBan], [SoLuong], [SoTien]) VALUES (N'NN01', N'PBH02', 4, 604000)
INSERT [dbo].[ChiTietPhieuBanHang] ([MaSach], [MaPhieuBan], [SoLuong], [SoTien]) VALUES (N'NN02', N'PBH01', 2, 360000)
INSERT [dbo].[ChiTietPhieuBanHang] ([MaSach], [MaPhieuBan], [SoLuong], [SoTien]) VALUES (N'NN02', N'PBH08', 2, 360000)
INSERT [dbo].[ChiTietPhieuBanHang] ([MaSach], [MaPhieuBan], [SoLuong], [SoTien]) VALUES (N'NN02', N'PBH09', 4, 720000)
INSERT [dbo].[ChiTietPhieuBanHang] ([MaSach], [MaPhieuBan], [SoLuong], [SoTien]) VALUES (N'NN02', N'PBH10', 4, 720000)
INSERT [dbo].[ChiTietPhieuBanHang] ([MaSach], [MaPhieuBan], [SoLuong], [SoTien]) VALUES (N'NN03', N'PBH08', 3, 414000)
GO
INSERT [dbo].[ChucVu] ([MaChucVu], [TenChucVu]) VALUES (N'NVBH', N'Nhân viên bán hàng')
INSERT [dbo].[ChucVu] ([MaChucVu], [TenChucVu]) VALUES (N'NVQL', N'Nhân viên quản lý')
GO
INSERT [dbo].[KhachHang] ([SDT_KH], [TenKhachHang], [SL_SachDaMua], [GiamGia]) VALUES (CAST(339836366 AS Numeric(10, 0)), N'Tân', 4, 0.05)
INSERT [dbo].[KhachHang] ([SDT_KH], [TenKhachHang], [SL_SachDaMua], [GiamGia]) VALUES (CAST(339836388 AS Numeric(10, 0)), N'DUY TÂN', 108, 0.15)
INSERT [dbo].[KhachHang] ([SDT_KH], [TenKhachHang], [SL_SachDaMua], [GiamGia]) VALUES (CAST(339836389 AS Numeric(10, 0)), N'Duy Tân nè!', 4, 0.05)
GO
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [DiaChi], [SDT], [Email]) VALUES (N'NCC01', N'Nhà sách Fahasa', N'128 đường Đào Cử, TT. Cần Thạnh, Huyện Cần Giờ, TP. HCM', CAST(837861684 AS Numeric(10, 0)), N'nscantho@fahasa.com.vn')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [DiaChi], [SDT], [Email]) VALUES (N'NCC02', N'Nhà sách Vĩnh Thụy', N'265 Hòa Bình P. Hiệp Tân Quận Tân Phú, TP.HCM', CAST(906708105 AS Numeric(10, 0)), N'congtyvanhoavinhthuy@gmail.com')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [DiaChi], [SDT], [Email]) VALUES (N'NCC03', N'Squishy', N'60-62 Lê Lợi, Q.1, TP. HCM', CAST(1900636467 AS Numeric(10, 0)), N'cskh@fahasa.com.vn')
INSERT [dbo].[NhaCungCap] ([MaNCC], [TenNCC], [DiaChi], [SDT], [Email]) VALUES (N'NCC04', N'Hệ thống nhà sách ABC', N'53/8 Vườn Lài - P.Phú Thọ Hoà - Quận Tân Phú - TP.Hồ Chí Minh', CAST(909354135 AS Numeric(10, 0)), N'support@newshop.vn')
GO
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNV], [GioiTinh], [NgaySinh], [NgayVaoLam], [DiaChi], [SDT], [MaChucVu], [TenDangNhap], [HinhAnh], [MatKhau], [LoaiTaiKhoan], [CCCD], [TrangThai]) VALUES (N'NV01', N'Võ Nguyễn Duy Tân', 1, CAST(N'2002-02-08' AS Date), CAST(N'2023-04-02' AS Date), N'Châu Thành, Tiền Giang', CAST(2866825005 AS Numeric(10, 0)), N'NVBH', N'vonguyenduytan', N'h1.png', N'123', 0, CAST(123456789123 AS Numeric(12, 0)), 1)
INSERT [dbo].[NhanVien] ([MaNhanVien], [TenNV], [GioiTinh], [NgaySinh], [NgayVaoLam], [DiaChi], [SDT], [MaChucVu], [TenDangNhap], [HinhAnh], [MatKhau], [LoaiTaiKhoan], [CCCD], [TrangThai]) VALUES (N'NV02', N'Nguyễn Phúc Vinh', 1, CAST(N'2002-01-01' AS Date), CAST(N'2023-01-20' AS Date), N'Tân Phú, TP.Hồ Chí Minh', CAST(957839475 AS Numeric(10, 0)), N'NVBH', N'nguyenphucvinh', N'h1.png', N'123', 0, CAST(123456789012 AS Numeric(12, 0)), 1)
GO
INSERT [dbo].[NhaXuatBan] ([MaNXB], [TenNXB]) VALUES (N'NXB001', N'Không xác định được nhà xuất bản')
INSERT [dbo].[NhaXuatBan] ([MaNXB], [TenNXB]) VALUES (N'NXB002', N'Nhà Xuất Bản Đà Nẵng')
GO
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH01', CAST(339836389 AS Numeric(10, 0)), N'NV01', 1417000, NULL, NULL, NULL, NULL)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH02', CAST(339836388 AS Numeric(10, 0)), N'NV01', 604000, NULL, NULL, NULL, NULL)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH03', CAST(339836389 AS Numeric(10, 0)), N'NV01', 0, NULL, NULL, NULL, NULL)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH04', CAST(339836389 AS Numeric(10, 0)), N'NV01', 0, CAST(N'2023-04-16T00:00:00.000' AS DateTime), NULL, NULL, NULL)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH05', CAST(339836389 AS Numeric(10, 0)), N'NV01', 0, CAST(N'2023-04-17T13:49:24.647' AS DateTime), 0, 0, NULL)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH06', CAST(339836389 AS Numeric(10, 0)), N'NV01', 0, CAST(N'2023-04-17T14:47:35.430' AS DateTime), 0, 0, NULL)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH07', CAST(339836389 AS Numeric(10, 0)), N'NV01', 0, CAST(N'2023-04-17T14:57:32.070' AS DateTime), 0, 0, NULL)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH08', CAST(339836389 AS Numeric(10, 0)), N'NV01', 774000, CAST(N'2023-04-17T15:07:35.317' AS DateTime), 0.05, 735300, 1)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH09', CAST(339836389 AS Numeric(10, 0)), N'NV01', 720000, CAST(N'2023-04-17T15:29:54.280' AS DateTime), 0.05, 684000, 1)
INSERT [dbo].[PhieuBanHang] ([MaPhieuBan], [SDT_KH], [MaNhanVien], [TongTien], [NgayMua], [GiamGia], [SoTienPhaiTra], [TrangThai]) VALUES (N'PBH10', CAST(339836366 AS Numeric(10, 0)), N'NV01', 720000, CAST(N'2023-04-17T15:34:16.667' AS DateTime), 0, 684000, 1)
GO
INSERT [dbo].[PhieuNhapHang] ([MaPhieuNhap], [MaNCC], [MaNhanVien], [NgayNhap]) VALUES (N'PNH01', N'NCC01', N'NV01', CAST(N'2023-04-19' AS Date))
INSERT [dbo].[PhieuNhapHang] ([MaPhieuNhap], [MaNCC], [MaNhanVien], [NgayNhap]) VALUES (N'PNH02', N'NCC01', N'NV01', CAST(N'2023-04-19' AS Date))
GO
INSERT [dbo].[Sach] ([MaSach], [MaNXB], [MaTheLoai], [SerialSach], [TenSach], [SoLuong], [LuotMua], [HinhAnh], [GiaBan], [TacGia]) VALUES (N'NN01', N'NXB002', N'TLNN', N'9786048462338', N'Giải thích ngữ pháp Tiếng Anh', 103, 9, N'GiaThichNguPhapTiengAnh.png', 151000, N'Mai Lan Hương; Hà Thanh Uyên')
INSERT [dbo].[Sach] ([MaSach], [MaNXB], [MaTheLoai], [SerialSach], [TenSach], [SoLuong], [LuotMua], [HinhAnh], [GiaBan], [TacGia]) VALUES (N'NN02', N'NXB002', N'TLNN', N'9835251410619', N'Hackers IELTS: READING', 108, 12, N'HackersIELTS_READING.png', 180000, N'Tuyết Nguyễn')
INSERT [dbo].[Sach] ([MaSach], [MaNXB], [MaTheLoai], [SerialSach], [TenSach], [SoLuong], [LuotMua], [HinhAnh], [GiaBan], [TacGia]) VALUES (N'NN03', N'NXB002', N'TLNN', N'9786048462383', N'Hackers IELTS Basic- Writing', 57, 3, N'HackersIELTSBasic_Writing.png', 138000, N'Tuyết Nguyễn')
INSERT [dbo].[Sach] ([MaSach], [MaNXB], [MaTheLoai], [SerialSach], [TenSach], [SoLuong], [LuotMua], [HinhAnh], [GiaBan], [TacGia]) VALUES (N'NN04', N'NXB002', N'TLNN', N'8935309501090', N'Hackers IELTS Basic- Listening', 50, 0, N'HackersIELTSBasicListening.png', 156000, N'Hường Phạm')
INSERT [dbo].[Sach] ([MaSach], [MaNXB], [MaTheLoai], [SerialSach], [TenSach], [SoLuong], [LuotMua], [HinhAnh], [GiaBan], [TacGia]) VALUES (N'TD01', N'NXB002', N'TLTD', N'9780312924584', N'Sự Im Lặng Của Bầy Cừu', 27, 0, N'SuImLangCuaBayCuu.png', 225000, N' Thomas Harris')
GO
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TL00', N'Không xác định thể loại')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TLNN', N'Sách học ngoại ngữ')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TLKT', N'Sách kinh tế')
INSERT [dbo].[TheLoai] ([MaTheLoai], [TenTheLoai]) VALUES (N'TLTD', N'Truyện dài')
GO
/****** Object:  Index [Uni_NCC_SDT]    Script Date: 4/20/2023 12:21:04 AM ******/
ALTER TABLE [dbo].[NhaCungCap] ADD  CONSTRAINT [Uni_NCC_SDT] UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Uni_NCC_Ten]    Script Date: 4/20/2023 12:21:04 AM ******/
ALTER TABLE [dbo].[NhaCungCap] ADD  CONSTRAINT [Uni_NCC_Ten] UNIQUE NONCLUSTERED 
(
	[TenNCC] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [Uni_CCCD_NhanVien]    Script Date: 4/20/2023 12:21:04 AM ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [Uni_CCCD_NhanVien] UNIQUE NONCLUSTERED 
(
	[CCCD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Uni_TendangNhap_NhanVien]    Script Date: 4/20/2023 12:21:04 AM ******/
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [Uni_TendangNhap_NhanVien] UNIQUE NONCLUSTERED 
(
	[TenDangNhap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Uni_NhaXuatBan]    Script Date: 4/20/2023 12:21:04 AM ******/
ALTER TABLE [dbo].[NhaXuatBan] ADD  CONSTRAINT [Uni_NhaXuatBan] UNIQUE NONCLUSTERED 
(
	[TenNXB] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Uni_SerialSach_Sach]    Script Date: 4/20/2023 12:21:04 AM ******/
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [Uni_SerialSach_Sach] UNIQUE NONCLUSTERED 
(
	[SerialSach] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Uni_TheLoai]    Script Date: 4/20/2023 12:21:04 AM ******/
ALTER TABLE [dbo].[TheLoai] ADD  CONSTRAINT [Uni_TheLoai] UNIQUE NONCLUSTERED 
(
	[TenTheLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietNhapSach] ADD  CONSTRAINT [df_ChiTietNhapSach_SoLuong]  DEFAULT ((0)) FOR [SoLuong]
GO
ALTER TABLE [dbo].[ChiTietPhieuBanHang] ADD  CONSTRAINT [df_ChiTietPhieuBanHang_SoLuong]  DEFAULT ((1)) FOR [SoLuong]
GO
ALTER TABLE [dbo].[ChiTietPhieuBanHang] ADD  CONSTRAINT [df_ChiTietPhieuBanHang_SoTien]  DEFAULT ((0)) FOR [SoTien]
GO
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [df_KhachHang_TenKhachHang]  DEFAULT (N'Khách hàng') FOR [TenKhachHang]
GO
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [df_KhachHang_SL_SachDaMua]  DEFAULT ((0)) FOR [SL_SachDaMua]
GO
ALTER TABLE [dbo].[KhachHang] ADD  CONSTRAINT [df_KhachHang_GiamGia]  DEFAULT ((0.0)) FOR [GiamGia]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [df_HinhAnh_NhanVien]  DEFAULT ('h1.png') FOR [HinhAnh]
GO
ALTER TABLE [dbo].[NhanVien] ADD  DEFAULT ((0)) FOR [LoaiTaiKhoan]
GO
ALTER TABLE [dbo].[NhanVien] ADD  CONSTRAINT [df_TrangThai_NhanVien]  DEFAULT ((1)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[PhieuBanHang] ADD  DEFAULT ((0)) FOR [TongTien]
GO
ALTER TABLE [dbo].[PhieuBanHang] ADD  CONSTRAINT [df_NgayMua_PhieuBanHang]  DEFAULT (getdate()) FOR [NgayMua]
GO
ALTER TABLE [dbo].[PhieuBanHang] ADD  CONSTRAINT [df_GiamGia_PhieuBanHang]  DEFAULT ((0)) FOR [GiamGia]
GO
ALTER TABLE [dbo].[PhieuBanHang] ADD  CONSTRAINT [df_SoTienPhaiTra_PhieuBanHang]  DEFAULT ((0)) FOR [SoTienPhaiTra]
GO
ALTER TABLE [dbo].[PhieuBanHang] ADD  CONSTRAINT [df_TrangThai_PhieuBanHang]  DEFAULT ((0)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[PhieuNhapHang] ADD  CONSTRAINT [df_NgayNhap]  DEFAULT (getdate()) FOR [NgayNhap]
GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [df_Sach_MaNXB]  DEFAULT ('NXB001') FOR [MaNXB]
GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [df_Sach_MaTheLoai]  DEFAULT ('TL001') FOR [MaTheLoai]
GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [df_Sach_SoLuong]  DEFAULT ((0)) FOR [SoLuong]
GO
ALTER TABLE [dbo].[Sach] ADD  CONSTRAINT [df_Sach_LuotMua]  DEFAULT ((0)) FOR [LuotMua]
GO
ALTER TABLE [dbo].[ChiTietNhapSach]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuNhap_Sach] FOREIGN KEY([MaSach])
REFERENCES [dbo].[Sach] ([MaSach])
GO
ALTER TABLE [dbo].[ChiTietNhapSach] CHECK CONSTRAINT [FK_ChiTietPhieuNhap_Sach]
GO
ALTER TABLE [dbo].[ChiTietNhapSach]  WITH CHECK ADD  CONSTRAINT [FK_PhieuNhapHang_ChiTietPhieuNhap] FOREIGN KEY([MaPhieuNhap])
REFERENCES [dbo].[PhieuNhapHang] ([MaPhieuNhap])
GO
ALTER TABLE [dbo].[ChiTietNhapSach] CHECK CONSTRAINT [FK_PhieuNhapHang_ChiTietPhieuNhap]
GO
ALTER TABLE [dbo].[ChiTietPhieuBanHang]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuBanHang_PhieuBanHang] FOREIGN KEY([MaPhieuBan])
REFERENCES [dbo].[PhieuBanHang] ([MaPhieuBan])
GO
ALTER TABLE [dbo].[ChiTietPhieuBanHang] CHECK CONSTRAINT [FK_ChiTietPhieuBanHang_PhieuBanHang]
GO
ALTER TABLE [dbo].[ChiTietPhieuBanHang]  WITH CHECK ADD  CONSTRAINT [FK_ChiTietPhieuBanHang_Sach] FOREIGN KEY([MaSach])
REFERENCES [dbo].[Sach] ([MaSach])
GO
ALTER TABLE [dbo].[ChiTietPhieuBanHang] CHECK CONSTRAINT [FK_ChiTietPhieuBanHang_Sach]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChucVu] FOREIGN KEY([MaChucVu])
REFERENCES [dbo].[ChucVu] ([MaChucVu])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChucVu]
GO
ALTER TABLE [dbo].[PhieuBanHang]  WITH CHECK ADD  CONSTRAINT [FK_PhieuBanHang_KhachHang] FOREIGN KEY([SDT_KH])
REFERENCES [dbo].[KhachHang] ([SDT_KH])
GO
ALTER TABLE [dbo].[PhieuBanHang] CHECK CONSTRAINT [FK_PhieuBanHang_KhachHang]
GO
ALTER TABLE [dbo].[PhieuBanHang]  WITH CHECK ADD  CONSTRAINT [FK_PhieuBanHang_NhanVien] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
GO
ALTER TABLE [dbo].[PhieuBanHang] CHECK CONSTRAINT [FK_PhieuBanHang_NhanVien]
GO
ALTER TABLE [dbo].[PhieuNhapHang]  WITH CHECK ADD  CONSTRAINT [FK_PhieuNhapHang_NhaCC] FOREIGN KEY([MaNCC])
REFERENCES [dbo].[NhaCungCap] ([MaNCC])
GO
ALTER TABLE [dbo].[PhieuNhapHang] CHECK CONSTRAINT [FK_PhieuNhapHang_NhaCC]
GO
ALTER TABLE [dbo].[PhieuNhapHang]  WITH CHECK ADD  CONSTRAINT [FK_PhieuNhapHang_NhanVien] FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[NhanVien] ([MaNhanVien])
GO
ALTER TABLE [dbo].[PhieuNhapHang] CHECK CONSTRAINT [FK_PhieuNhapHang_NhanVien]
GO
ALTER TABLE [dbo].[Sach]  WITH CHECK ADD  CONSTRAINT [FK_Sach_NhaXuatBan] FOREIGN KEY([MaNXB])
REFERENCES [dbo].[NhaXuatBan] ([MaNXB])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FK_Sach_NhaXuatBan]
GO
ALTER TABLE [dbo].[Sach]  WITH CHECK ADD  CONSTRAINT [FK_Sach_TheLoaiSach] FOREIGN KEY([MaTheLoai])
REFERENCES [dbo].[TheLoai] ([MaTheLoai])
GO
ALTER TABLE [dbo].[Sach] CHECK CONSTRAINT [FK_Sach_TheLoaiSach]
GO
/****** Object:  StoredProcedure [dbo].[SP_InsertOneGoodsDeliveryNote]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[SP_InsertOneGoodsDeliveryNote] (@MaNhaCungCap varchar(6), @MaNhanVien varchar(5) )
as
		declare @MaPhieuNhapInt int, @MaPhieuNhapChar varchar(10);
		if exists(Select*from PhieuNhapHang)
		begin
			set @MaPhieuNhapInt=(Select MAX(tb1.ma) from (select SUBSTRING(MaPhieuNhap,4,6) as 'ma' from PhieuNhapHang) as tb1);
		end
		else
		begin
			set @MaPhieuNhapInt=0;
		end
		if(@MaPhieuNhapInt<9)
			begin
				set @MaPhieuNhapChar= 'PNH0'+ CONVERT(varchar(6),@MaPhieuNhapInt+1);
				insert into PhieuNhapHang(MaPhieuNhap, MaNCC, MaNhanVien, NgayNhap) values
				(@MaPhieuNhapChar, @MaNhaCungCap,@MaNhanVien,GETDATE())
			end
		else
			begin
				set @MaPhieuNhapChar= 'PNH'+ CONVERT(varchar(6),@MaPhieuNhapInt+1);
				insert into PhieuNhapHang(MaPhieuNhap, MaNCC, MaNhanVien, NgayNhap) values
				(@MaPhieuNhapChar, @MaNhaCungCap,@MaNhanVien,GETDATE());
			end

GO
/****** Object:  StoredProcedure [dbo].[SP_InsertOneProduct]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[SP_InsertOneProduct] (@MaNXB varchar(6), @MaTheLoai varchar(5), @SerialSach numeric(13,0) ,
@TenSach nvarchar(256) ,@SoLuong int , @HinhAnh varchar(50), @GiaBan float, @TacGia nvarchar(512) )
as
		declare @MaSachInt int, @MaSachChar varchar(20);
		set @MaSachInt=(Select MAX(tb1.ma) from (select SUBSTRING(MaSach,3,2) as 'ma' from Sach) as tb1);
		if(@MaSachInt<9)
			begin
				set @MaSachChar= SUBSTRING(@MaTheLoai,3,2)+'0'+ CONVERT(varchar(3),@MaSachInt+1);
				insert into Sach(MaSach, MaNXB, MaTheLoai, SerialSach, TenSach, SoLuong, LuotMua, HinhAnh, GiaBan, TacGia) values
				(@MaSachChar, @MaNXB, @MaTheLoai, @SerialSach, @TenSach, @SoLuong, 0, @HinhAnh, @GiaBan, @TacGia)
			end
		else
			begin
				set @MaSachChar= SUBSTRING(@MaTheLoai,3,2)+ CONVERT(varchar(3),@MaSachInt+1);
				insert into Sach(MaSach, MaNXB, MaTheLoai, SerialSach, TenSach, SoLuong, LuotMua, HinhAnh, GiaBan) values
				(@MaSachChar, @MaNXB, @MaTheLoai, @SerialSach, @TenSach, @SoLuong, 0, @HinhAnh, @GiaBan)
			end

GO
/****** Object:  StoredProcedure [dbo].[SP_InsertOneQuittance]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[SP_InsertOneQuittance] (@sdtKhachHang numeric(10,0), @MaNhanVien varchar(5) )
as
		declare @MaPhieuBanInt int, @MaPhieuBanChar varchar(10);
		set @MaPhieuBanInt=(Select MAX(tb1.ma) from (select SUBSTRING(MaPhieuBan,4,6) as 'ma' from PhieuBanHang) as tb1);
		if(@MaPhieuBanInt is NULL)
			begin
				set @MaPhieuBanInt=0;
			end
		if(@MaPhieuBanInt<9)
			begin
				set @MaPhieuBanChar= 'PBH0'+ CONVERT(varchar(6),@MaPhieuBanInt+1);
				insert into PhieuBanHang(MaPhieuBan, SDT_KH, MaNhanVien) values
				(@MaPhieuBanChar, @sdtKhachHang,@MaNhanVien)
			end
		else
			begin
				set @MaPhieuBanChar= 'PBH'+ CONVERT(varchar(6),@MaPhieuBanInt+1);
				insert into PhieuBanHang(MaPhieuBan, SDT_KH, MaNhanVien) values
				(@MaPhieuBanChar, @sdtKhachHang,@MaNhanVien)
			end

GO
/****** Object:  StoredProcedure [dbo].[SP_InsertOneSalePerson]    Script Date: 4/20/2023 12:21:04 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[SP_InsertOneSalePerson] (@TenNV nvarchar(50), @GioiTinh bit, @NgaySinh date, @NgayVaoLam date,
									@DiaChi nvarchar(256), @SDT numeric(10,0), @MaChucVu varchar(5), @TenDangNhap varchar(30),
									@HinhAnh varchar(50), @MatKhau varchar(30), @CCCD numeric(12,0))
as
		declare @MaNhanVienInt int, @MaNhanVienChar varchar(5);
		if exists(Select*from PhieuNhapHang)
		begin
			set @MaNhanVienInt=(Select MAX(tb1.ma) from (select SUBSTRING(MaNhanVien,3,5) as 'ma' from NhanVien) as tb1);
		end
		else
		begin
			set @MaNhanVienInt=0;
		end
		if(@MaNhanVienInt<9)
			begin
				set @MaNhanVienChar= 'NV0'+ CONVERT(varchar(2),@MaNhanVienInt+1);
				SET DATEFORMAT dmy;  

				insert into NhanVien(MaNhanVien,TenNV,GioiTinh,NgaySinh,NgayVaoLam,DiaChi,SDT,MaChucVu
				,TenDangNhap,HinhAnh,MatKhau,CCCD) values
				(@MaNhanVienChar,@TenNV,1,@NgaySinh,@NgayVaoLam,@DiaChi, @SDT, @MaChucVu,@TenDangNhap,@HinhAnh,
				@MatKhau,@CCCD)
			end
		else
			begin
				set @MaNhanVienChar= 'NV'+ CONVERT(varchar(3),@MaNhanVienInt+1);
				SET DATEFORMAT dmy;  
				  
				insert into NhanVien(MaNhanVien,TenNV,GioiTinh,NgaySinh,NgayVaoLam,DiaChi,SDT,MaChucVu
				,TenDangNhap,HinhAnh,MatKhau,CCCD) values
				(@MaNhanVienChar,@TenNV,1,@NgaySinh,@NgayVaoLam,@DiaChi, @SDT, @MaChucVu,@TenDangNhap,@HinhAnh,
				@MatKhau,@CCCD)
			end

GO
