/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlycuahangbansach.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import quanlycuahangbansach.DAO.CategoryDAO;
import quanlycuahangbansach.DAO.DoanhThuThangDAO;
import quanlycuahangbansach.DAO.GoodsDeliveryNoteDAO;
import quanlycuahangbansach.DAO.GoodsReceivedNoteDAO;
import quanlycuahangbansach.DAO.InvoiceDetailsDAO;
import quanlycuahangbansach.DAO.PositionDAO;
import quanlycuahangbansach.DAO.ProductDAO;
import quanlycuahangbansach.DAO.PublisherDAO;
import quanlycuahangbansach.DAO.SalesPersonDAO;
import quanlycuahangbansach.DAO.SalesSlipDAO;
import quanlycuahangbansach.DAO.SupplierDAO;
import quanlycuahangbansach.Models.Category;
import quanlycuahangbansach.Models.CostomModel;
import quanlycuahangbansach.Models.CustomModelIconInFirstColumn;
import quanlycuahangbansach.Models.GoodsDeliveryNote;
import quanlycuahangbansach.Models.GoodsReceivedNote;
import quanlycuahangbansach.Models.InvoiceDetails;
import quanlycuahangbansach.Models.Position;
import quanlycuahangbansach.Models.Publisher;
import quanlycuahangbansach.Models.Product;
import quanlycuahangbansach.Models.SalePerson;
import quanlycuahangbansach.Models.SalesSlip;
import quanlycuahangbansach.Models.Supplier;

/**
 *
 * @author PC
 */
public class AdminPageGUI extends javax.swing.JFrame {

    String userName;
    SalePerson s;
    Color defaultColor, clickColor;
    
    
    //defaulttable model;
    DefaultTableModel dtm_tbSanPham_TabQLSanPham =new DefaultTableModel();
    DefaultTableModel dtm_tbDanhSachPhieuBan_TabQLBanHang=new DefaultTableModel();
    String ImageUpLoad_TabQLSanPham, ImageUpLoad_TabQLNhanVien;
    File imageUpLoadPath_NhanVien, imageUpLoadPath_SanPham;

            
    
    /**
     * Creates new form AdminPageGUI
     */
    public AdminPageGUI(String userName) {
        initComponents();
        s=SalesPersonDAO.getOneSalePersonByUsername(userName);
        setColorForButton();
        
        LoadTabQLSanPhan();
        //LoadComboBoxNhaCungCap_TabQLNhapHang();
        
        
        
    }

    private AdminPageGUI() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void LoadTabQLSanPhan()
    {
        LoadTableSanPham_TabQLSanPham();
        LoadComboboxTheLoai_TabQLSanPham();
        LoadComboboxNhaXuatBan_TabQLSanPham();
    }
    
    //=========================================TAB QUAN LY SAN PHAM HEAD=================================
    private void LoadComboboxTheLoai_TabQLSanPham()
    {
        cbbTheLoai_TabQLSanPham.removeAllItems();
        ArrayList<Category> ListCategory=CategoryDAO.getAll();
       
        Category cate=new Category();
        for(Category category : ListCategory)
        {
            if(category.getMaTheLoai().equals("TL00"))
            {
                cate=new Category(category);
            }
            cbbTheLoai_TabQLSanPham.addItem(category);
        }
        Category c =new Category();
        c.setMaTheLoai("addNew");
        c.setTenTheLoai("Thêm mới thể loại");
        cbbTheLoai_TabQLSanPham.addItem(c);
        DefaultComboBoxModel<Category> defaultComboBoxModel=(DefaultComboBoxModel) cbbTheLoai_TabQLSanPham.getModel();
        defaultComboBoxModel.setSelectedItem(cate);
        
    }
    private void LoadComboboxNhaXuatBan_TabQLSanPham()
    {
        //cbbNhaXuatBan_TabQLSanPham.setPreferredSize(new Dimension(202,25));
        ArrayList<Publisher> danhSachNhaXuatBan=PublisherDAO.getAll();
        cbbNhaXuatBan_TabQLSanPham.removeAllItems();
        Publisher pub=new Publisher();
        for(Publisher nhaXuatBan : danhSachNhaXuatBan)
        {
            if(nhaXuatBan.getMaNhaXuatBan().equals("NXB001"))
            {
                pub=nhaXuatBan;
            }
            cbbNhaXuatBan_TabQLSanPham.addItem(nhaXuatBan);
        }
        Publisher nxb=new Publisher();
        nxb.setTenNhaXuatBan("Thêm nhà xuất bản mới");
        nxb.setMaNhaXuatBan("addNew");
        cbbNhaXuatBan_TabQLSanPham.addItem(nxb);
        DefaultComboBoxModel<Publisher> defaultComboBoxModel=(DefaultComboBoxModel) cbbNhaXuatBan_TabQLSanPham.getModel();
        defaultComboBoxModel.setSelectedItem(pub);
    }
    private void LoadTableSanPham_TabQLSanPham()
    {
        String[] header_tbSanPham_TabQLSanPham={"Mã sách", "Nhà Xuất Bản", "Thể loại", 
            "Serial", "Tên sách","Số lượng", "Lượt mua", " Hình ảnh","Giá bán","Tên ảnh","Tác giả"};
        //dtm_tbSanPham_TabQLSanPham.setColumnIdentifiers(header_tbSanPham_TabQLSanPham);
        
        //tb_SanPham_TabQLSanPham.setModel(dtm_tbSanPham_TabQLSanPham);
        ArrayList<Product> danhSachSanPham=ProductDAO.getAll();
        Object[][] data = new Object[danhSachSanPham.size()][11];
        for(int i=0;i<danhSachSanPham.size();i++)
        {
            Product sp=danhSachSanPham.get(i);
            data[i][0]=sp.getMaSach();
            data[i][1]=sp.getMaNhaXuatBan();
            data[i][2]=sp.getMaTheLoai();
            data[i][3]=sp.getSerialSach();
            data[i][4]=sp.getTenSach();
            data[i][5]=sp.getSoLuong();
            data[i][6]=sp.getLuotMua();
            if(sp.getHinhAnh()!=null)
            {
                ImageIcon imageIcon=new ImageIcon("Images/"+sp.getHinhAnh());
                Image image = imageIcon.getImage(); // transform it 
                Image newImage = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                imageIcon = new ImageIcon(newImage);
                data[i][7]=imageIcon;
            }
            else
            {
                data[i][7]=null;
            }
            data[i][8]=sp.getGiaBan();
            data[i][9]=sp.getHinhAnh();
            data[i][10]=sp.getTacGia();
        }
        CostomModel costomModel=new CostomModel(data,header_tbSanPham_TabQLSanPham);
        tb_SanPham_TabQLSanPham.setModel(costomModel);
        tb_SanPham_TabQLSanPham.setRowHeight(150);
        tb_SanPham_TabQLSanPham.getColumnModel().getColumn(7).setPreferredWidth(150);
    
        tb_SanPham_TabQLSanPham.removeColumn(tb_SanPham_TabQLSanPham.getColumnModel().getColumn(9));
        tb_SanPham_TabQLSanPham.removeColumn(tb_SanPham_TabQLSanPham.getColumnModel().getColumn(9));

    
    }
    //=========================================TAB QUAN LY SAN PHAM FOOT=================================
    
     //=========================================TAB QUAN LY NHAP HANG HEAD=================================
    private void clearChiTietNhapHang_TabQLNhapHang()
    {
        edtMaSach_TabQLNhapHang.setText("");
        edtSoLuong_TabQLNhapHang.setText("");
    }
    private void LoadComboBoxNhaCungCap_TabQLNhapHang()
    {
        cbbNhaCungCap_TabQLNhapHang.removeAllItems();
        Supplier supplier =new Supplier();
        cbbNhaCungCap_TabQLNhapHang.addItem(supplier);
        ArrayList<Supplier> dsNhaCungCap=SupplierDAO.getAll();
        if(dsNhaCungCap.size()>=0)
        {
            for(Supplier s:dsNhaCungCap)
            {
                cbbNhaCungCap_TabQLNhapHang.addItem(s);
            }
        }
    }
    private void LoadComboBoxTheLoaiSach_TabQLNhapHang()
    {
        ArrayList<Category> dsTheLoai=CategoryDAO.getAll();
        cbbTheLoaiSach_TabQLNhapHang.removeAllItems();
        Category cate=new Category();
        for(Category c: dsTheLoai)
        {
            if(c.getMaTheLoai().equals("TL00"))
            {
                cate=c;
            }
            cbbTheLoaiSach_TabQLNhapHang.addItem(c);
        }
        DefaultComboBoxModel<Category> defaultComboBoxModel=(DefaultComboBoxModel) cbbTheLoaiSach_TabQLNhapHang.getModel();
        defaultComboBoxModel.setSelectedItem(cate);
    }
    
    private void LoadComboBoxSach_TabQLNhapHang(String maTheLoai)
    {
        cbbSach_TabQLNhapHang.removeAllItems();
        ArrayList<Product> dsSachSanPham=ProductDAO.getListByIDCategoryValue(maTheLoai);
        for(Product p:dsSachSanPham)
        {
            cbbSach_TabQLNhapHang.addItem(p);
        }
        
        
    }

    private void LoadTableChiTietNhapHang_TabQLNhapHang(ArrayList<GoodsReceivedNote> values)
    {
        String[] header_tbChiTietNhapHang_TabQLNhapHang={"Hình ảnh", "Mã phiếu nhập", "Mã Sách", "Tên sách", "Số lượng"};
        Object[][] data = new Object[values.size()][5];
        for(int i=0;i<values.size();i++)
        {
            
            GoodsReceivedNote g=values.get(i);
            Product sp=ProductDAO.getOneById(g.getMaSach());
            if(sp!=null)
            {
                if(sp.getHinhAnh()!=null)
                {
                    ImageIcon imageIcon=new ImageIcon("Images/"+sp.getHinhAnh());
                    Image image = imageIcon.getImage(); // transform it 
                    Image newImage = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                    imageIcon = new ImageIcon(newImage);
                    data[i][0]=imageIcon;
                }
                else
                {
                    data[i][0]=null;
                }
            }
            else
            {
                data[i][0]=null;
            }
            data[i][1]=g.getMaPhieuNhap();
            data[i][2]=g.getMaSach();
            if(sp!=null)
            {
                data[i][3]=sp.getTenSach();
            }
            else
            {
                 data[i][3]="";
            }
            
            data[i][4]=g.getSoLuong();
            
        }
        CustomModelIconInFirstColumn costomModel=new CustomModelIconInFirstColumn(data,header_tbChiTietNhapHang_TabQLNhapHang);
        tbChiTietNhapHang_TabQLNhapHang.setModel(costomModel);
        tbChiTietNhapHang_TabQLNhapHang.setRowHeight(150);
        tbChiTietNhapHang_TabQLNhapHang.getColumnModel().getColumn(0).setPreferredWidth(150);
    }
    //=========================================TAB QUAN LY NHAP HANG FOOT=================================
    
     //=========================================TAB QUAN LY BAN HANG HEAD=================================
    private void LoadComboBoxTimTheoNhanVien_TabQLBanHang()
    {
        ArrayList<SalePerson> dsNhanVien=SalesPersonDAO.getAll();
        cbbTimTheoNhanVien_TabQLBanHang.removeAllItems();
        cbbTimTheoNhanVien_TabQLBanHang.addItem(new SalePerson());
        for(SalePerson s:dsNhanVien)
        {
            cbbTimTheoNhanVien_TabQLBanHang.addItem(s);
        }
    }
    private void LoadtableChiTietPhieuBan_TabQLBanHang(String MaPhieuBan)
    {
        ArrayList<InvoiceDetails> dsChiTietPhieuBanHang=InvoiceDetailsDAO.getListByIDSalesSlip(MaPhieuBan);
        tbChiTietPhieuBan_TabQLBanHang.removeAll();
        String[] header_tbChiTietPhieuBanHang_TabQLBanHang={"Hình ảnh", "Tên sách", "Mã Sách",
            "Mã phiếu bán", "Số lượng","Số tiền"};
        Object[][] data = new Object[dsChiTietPhieuBanHang.size()][6];
        for(int i=0;i<dsChiTietPhieuBanHang.size();i++)
        {
            
            InvoiceDetails in= dsChiTietPhieuBanHang.get(i);
            Product sp=ProductDAO.getOneById(in.getMaSach());
            if(sp!=null)
            {
                if(sp.getHinhAnh()!=null)
                {
                    ImageIcon imageIcon=new ImageIcon("Images/"+sp.getHinhAnh());
                    Image image = imageIcon.getImage(); // transform it 
                    Image newImage = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                    imageIcon = new ImageIcon(newImage);
                    data[i][0]=imageIcon;
                    data[i][1]=sp.getTenSach();
                    data[i][2]=sp.getMaSach();
                }
                else
                {
                    data[i][0]=null;
                    data[i][1]="";
                    data[i][2]=in.getMaSach();
                }
            }
            else
            {
                data[i][0]=null;
                data[i][1]="";
                data[i][2]=in.getMaSach();
            }
            data[i][3]=in.getMaPhieuBan();
            data[i][4]=in.getSoLuong();
            data[i][5]=in.getTongTien();
            
            
        }
        CustomModelIconInFirstColumn costomModel=new CustomModelIconInFirstColumn(data,header_tbChiTietPhieuBanHang_TabQLBanHang);
        tbChiTietPhieuBan_TabQLBanHang.setModel(costomModel);
        tbChiTietPhieuBan_TabQLBanHang.setRowHeight(100);
        tbChiTietPhieuBan_TabQLBanHang.getColumnModel().getColumn(0).setPreferredWidth(100);
    }
    
    private void LoadTableDanhSachPhieuBan_TabQLBanHang(ArrayList<SalesSlip> dsPhieuBanHang)
    {
        tbDanhSachPhieuBan_TabQLBanHang.removeAll();
        String[] header_tbDanhSachPhieuBan_TabQLNhapHang={"Mã phiếu bán","SDT Khách","Mã nhân viên","Ngày mua",
        "Tổng tiền hóa đơn","Giảm giá","Số tiền phải trả","Trạng thái"};
        dtm_tbDanhSachPhieuBan_TabQLBanHang.setColumnIdentifiers(header_tbDanhSachPhieuBan_TabQLNhapHang);
        Object[][] data = new Object[dsPhieuBanHang.size()][8];
        for(int i=0;i<dsPhieuBanHang.size();i++)
        {
            SalesSlip s=dsPhieuBanHang.get(i);
            data[i][0]=s.getMaPhieuBanHang();
            data[i][1]=s.getSoDienThoaiKhachHang();
            SalePerson nv=SalesPersonDAO.getOneSalePersonByIDSalePerson(s.getMaNhanVien());
            if(nv!=null)
            {
                data[i][2]=nv.getTenNhanVien()+"("+nv.getMaNhanVien()+")";
            }
            else
            {
                data[i][2]=s.getMaNhanVien();
                
            }
            data[i][3]=s.getNgayMua();
            data[i][4]=s.getTongTien();
            data[i][5]=s.getGiamGia();
            data[i][6]=s.getSoTienPhiaTra();
            data[i][7]=s.getTrangThai();
        }
        tbDanhSachPhieuBan_TabQLBanHang.setModel(dtm_tbDanhSachPhieuBan_TabQLBanHang);
        dtm_tbDanhSachPhieuBan_TabQLBanHang.setDataVector(data,header_tbDanhSachPhieuBan_TabQLNhapHang);
        tbDanhSachPhieuBan_TabQLBanHang.setModel(dtm_tbDanhSachPhieuBan_TabQLBanHang);
        tbDanhSachPhieuBan_TabQLBanHang.setRowHeight(50);
        tbDanhSachPhieuBan_TabQLBanHang.getColumnModel().getColumn(0).setPreferredWidth(90);
        tbDanhSachPhieuBan_TabQLBanHang.getColumnModel().getColumn(1).setPreferredWidth(140);
        tbDanhSachPhieuBan_TabQLBanHang.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbDanhSachPhieuBan_TabQLBanHang.getColumnModel().getColumn(3).setPreferredWidth(195);
        tbDanhSachPhieuBan_TabQLBanHang.getColumnModel().getColumn(4).setPreferredWidth(110);
        tbDanhSachPhieuBan_TabQLBanHang.getColumnModel().getColumn(5).setPreferredWidth(60);
        tbDanhSachPhieuBan_TabQLBanHang.getColumnModel().getColumn(6).setPreferredWidth(100);
        tbDanhSachPhieuBan_TabQLBanHang.getColumnModel().getColumn(7).setPreferredWidth(105);
    }

    //======================================================================================
    //==================thong ke doanh thu==================================================
    private void LoadTabQLThongKeDoanhThu()
    {
        int SoSachDangKD=ProductDAO.getCountProduct();
        int SoSachDaBan=ProductDAO.getSumProductWereSale();
        int SoSachDaBanTrongThang=ProductDAO.getSumProductWereSaleInMonth();
        float doanhThuThang=ProductDAO.getMoneyInMonth();
        lblSachDangKD_TabQLDanhThu.setText(String.valueOf(SoSachDangKD));
        lblSachDangDaBan_TabQLDanhThu.setText(String.valueOf(SoSachDaBan));
        lblSachDangDaBanTrongThang_TabQLDanhThu.setText(String.valueOf(SoSachDaBanTrongThang));
        lblDoanhThuThang_TabQLDanhThu.setText(String.valueOf(doanhThuThang));
    }
    private void LoadJtreeChart()
    {
        float t1=DoanhThuThangDAO.getRevenue1();
        float t2=DoanhThuThangDAO.getRevenue2();
        float t3=DoanhThuThangDAO.getRevenue3();
        float t4=DoanhThuThangDAO.getRevenue4();
        float t5=DoanhThuThangDAO.getRevenue5();
        float t6=DoanhThuThangDAO.getRevenue6();
        float t7=DoanhThuThangDAO.getRevenue7();
        float t8=DoanhThuThangDAO.getRevenue8();
        float t9=DoanhThuThangDAO.getRevenue9();
        float t10=DoanhThuThangDAO.getRevenue10();
        float t11=DoanhThuThangDAO.getRevenue11();
        float t12=DoanhThuThangDAO.getRevenue12();
        DefaultCategoryDataset dataSet=new DefaultCategoryDataset();
        dataSet.setValue(t1, "Đơn vị tiền(VND)", "Tháng 1");
        dataSet.setValue(t2, "Đơn vị tiền(VND)", "Tháng 2");
        dataSet.setValue(t3, "Đơn vị tiền(VND)", "Tháng 3");
        dataSet.setValue(t4, "Đơn vị tiền(VND)", "Tháng 4");
        dataSet.setValue(t5, "Đơn vị tiền(VND)", "Tháng 5");
        dataSet.setValue(t6, "Đơn vị tiền(VND)", "Tháng 6");
        dataSet.setValue(t7, "Đơn vị tiền(VND)", "Tháng 7");
        dataSet.setValue(t8, "Đơn vị tiền(VND)", "Tháng 8");
        dataSet.setValue(t9, "Đơn vị tiền(VND)", "Tháng 9");
        dataSet.setValue(t10, "Đơn vị tiền(VND)", "Tháng 10");
        dataSet.setValue(t11, "Đơn vị tiền(VND)", "Tháng 11");
        dataSet.setValue(t12, "Đơn vị tiền(VND)", "Tháng 12");
        JFreeChart chart=ChartFactory.createBarChart("Biểu đồ doanh thu theo tháng", "Doanh thu",
                "Đơn vị tiền(VND)", dataSet,PlotOrientation.VERTICAL,false,true,false);
        CategoryPlot cp=chart.getCategoryPlot();
        cp.setRangeGridlinePaint(Color.BLACK);
        ChartFrame frame=new ChartFrame("Biểu đồ doanh thu theo tháng", chart);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(1100,700);
    }
    
    private void setColorForButton()
    {
        defaultColor=new Color(255,255,255);
        clickColor=new Color(255,0,0);
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tableTab = new javax.swing.JTabbedPane();
        tabQLSanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_SanPham_TabQLSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        edtMaSach_TabQLSanPham = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        edtSerial_TabQLSanPham = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        edtSoLuongDanBan_TabQLSanPham = new javax.swing.JTextPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        edtSoLuongTon_TabQLSanPham = new javax.swing.JTextPane();
        jlabelHinhAnh_TabQLSanPham = new javax.swing.JLabel();
        btnChonAnh_TabQLSanPham = new javax.swing.JButton();
        cbbTheLoai_TabQLSanPham = new javax.swing.JComboBox<>();
        cbbNhaXuatBan_TabQLSanPham = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        edtTenSach_TabQLSanPham = new javax.swing.JTextPane();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        edtGiaBan_TabQLSanPham = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        edtTacGia_TabQLSanPham = new javax.swing.JTextPane();
        jLabel15 = new javax.swing.JLabel();
        btnThem_TabQLSanPham = new javax.swing.JButton();
        btnSua_TabQLSanPham = new javax.swing.JButton();
        btnXoa_TabQLSanPham = new javax.swing.JButton();
        tabNhapHang = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnTaoMoiPNH_TabQLNhapHang = new javax.swing.JButton();
        btnXoaPNH_TabQLNhapHang = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        edtMaPhieuNhap_TabQLNhapHang = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        edtMaNhanVien_TabQLNhapHang = new javax.swing.JTextPane();
        edtNgayNhap_TabQLNhapHang = new com.toedter.calendar.JDateChooser();
        cbbNhaCungCap_TabQLNhapHang = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbChiTietNhapHang_TabQLNhapHang = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbbTheLoaiSach_TabQLNhapHang = new javax.swing.JComboBox<>();
        cbbSach_TabQLNhapHang = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        edtMaSach_TabQLNhapHang = new javax.swing.JTextPane();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        edtSoLuong_TabQLNhapHang = new javax.swing.JTextPane();
        btnThemChiTietNhapHang_TabQLNhapHang = new javax.swing.JButton();
        btnSuaChiTietNhapHang_TabQLNhapHang = new javax.swing.JButton();
        btnXoaChiTietNhapHang_TabQLNhapHang = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        cbbTimTheo_TabQLNhapHang = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        edtThongTinTimKiem_TabQLNhapHang = new javax.swing.JTextPane();
        jLabel20 = new javax.swing.JLabel();
        cbbKetQuaTimKiem_TabQLNhapHang = new javax.swing.JComboBox<>();
        btnTimKiem_TabQLNhapHang = new javax.swing.JButton();
        edtNgayTimKiem_TabQLPhieuNhap = new com.toedter.calendar.JDateChooser();
        tabBanHang = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        cbbTimTheoNhanVien_TabQLBanHang = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        btnTimKiemTheoMaNV_TabQLBanHang = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        btnTimKiemTheoNgay_TabQLBanHang = new javax.swing.JButton();
        btnXuatTatCa_TabQLBanHang = new javax.swing.JButton();
        edtNgayBatDau_TablQLBanHang = new com.toedter.calendar.JDateChooser();
        edtNgayKetThuc_TablQLBanHang = new com.toedter.calendar.JDateChooser();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tbDanhSachPhieuBan_TabQLBanHang = new javax.swing.JTable();
        btnXuatThongKeDoanhSoBanHang_TabQLBanHang = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        tbChiTietPhieuBan_TabQLBanHang = new javax.swing.JTable();
        tabQLNhanVien = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        cbbTimKiemTheoLoai_TabQLNhanVien = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        btnTimKiem_TabQLNhanVien = new javax.swing.JButton();
        cbbKetQua_TabQLNhanVien = new javax.swing.JComboBox<>();
        jScrollPane29 = new javax.swing.JScrollPane();
        txt_data = new javax.swing.JTextPane();
        jLabel31 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        labelHinhAnhNV_TabQLNhanVien = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        edtMaNV_TabQLNhanVien = new javax.swing.JTextPane();
        jScrollPane21 = new javax.swing.JScrollPane();
        edtTenNV_TabQLNhanVien = new javax.swing.JTextPane();
        jScrollPane23 = new javax.swing.JScrollPane();
        edtCCCD_TabQLNhanVien = new javax.swing.JTextPane();
        jScrollPane27 = new javax.swing.JScrollPane();
        edtSoDienThoaiNV_TabQLNhanVien = new javax.swing.JTextPane();
        jScrollPane28 = new javax.swing.JScrollPane();
        edtTenDangNhapNV_TabQlNhanVien = new javax.swing.JTextPane();
        jScrollPane32 = new javax.swing.JScrollPane();
        edtQueQuan_TabNhaVien = new javax.swing.JTextPane();
        btnThemNhaVien_TabQLNhanVien = new javax.swing.JButton();
        btnSuaNV_TabQLNhanVien = new javax.swing.JButton();
        btnXoaNV_tabQLNhanVien = new javax.swing.JButton();
        cbbChucVuNV_TabQLNhanVien = new javax.swing.JComboBox<>();
        jLabel45 = new javax.swing.JLabel();
        cbbGioiTinh_TabQLNhanVien = new javax.swing.JComboBox<>();
        cbbLoaiTaiKhoan_TabQLNhanVien = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        cbbTrangThai_TabQLNhanVien = new javax.swing.JComboBox<>();
        edtNgaySinh_TabQLNhanVien = new com.toedter.calendar.JDateChooser();
        edtNgayVaoLam_TabQLNhanVien = new com.toedter.calendar.JDateChooser();
        btnChonAnh_TabQLNhanVien = new javax.swing.JButton();
        edtMatKhauNV_TabQLNhanVien = new javax.swing.JPasswordField();
        tabQLDoanhThu = new javax.swing.JPanel();
        btnLoadChart_TabQLDoanhThu = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        lblSachDangDaBan_TabQLDanhThu = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        lblSachDangKD_TabQLDanhThu = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        lblDoanhThuThang_TabQLDanhThu = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        lblSachDangDaBanTrongThang_TabQLDanhThu = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        tabQLHoaDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnNhapHang = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnThongKeDoanhThu = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tableTab.setBackground(new java.awt.Color(255, 255, 255));
        tableTab.setMinimumSize(new java.awt.Dimension(1280, 720));

        tabQLSanPham.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 255, 204));
        jLabel1.setText("Quản lý sản phẩm");

        tb_SanPham_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tb_SanPham_TabQLSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tb_SanPham_TabQLSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_SanPham_TabQLSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_SanPham_TabQLSanPham);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết sách", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Mã sách:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Serial:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Thể loại:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nhà xuất bản:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Số lượng đã bán:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Số lượng tồn:");

        edtMaSach_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtMaSach_TabQLSanPham.setEnabled(false);
        edtMaSach_TabQLSanPham.setMinimumSize(new java.awt.Dimension(100, 25));
        edtMaSach_TabQLSanPham.setPreferredSize(new java.awt.Dimension(100, 25));
        jScrollPane2.setViewportView(edtMaSach_TabQLSanPham);

        edtSerial_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtSerial_TabQLSanPham.setMinimumSize(new java.awt.Dimension(100, 25));
        edtSerial_TabQLSanPham.setPreferredSize(new java.awt.Dimension(100, 25));
        jScrollPane5.setViewportView(edtSerial_TabQLSanPham);

        edtSoLuongDanBan_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtSoLuongDanBan_TabQLSanPham.setMinimumSize(new java.awt.Dimension(100, 25));
        edtSoLuongDanBan_TabQLSanPham.setPreferredSize(new java.awt.Dimension(100, 25));
        jScrollPane6.setViewportView(edtSoLuongDanBan_TabQLSanPham);

        edtSoLuongTon_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtSoLuongTon_TabQLSanPham.setMinimumSize(new java.awt.Dimension(100, 25));
        edtSoLuongTon_TabQLSanPham.setPreferredSize(new java.awt.Dimension(100, 25));
        jScrollPane7.setViewportView(edtSoLuongTon_TabQLSanPham);

        jlabelHinhAnh_TabQLSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlycuahangbansach/Images/btnHome.png"))); // NOI18N
        jlabelHinhAnh_TabQLSanPham.setText("jLabel10");

        btnChonAnh_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnChonAnh_TabQLSanPham.setForeground(new java.awt.Color(0, 0, 102));
        btnChonAnh_TabQLSanPham.setText("Chọn ảnh");
        btnChonAnh_TabQLSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnh_TabQLSanPhamActionPerformed(evt);
            }
        });

        cbbTheLoai_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTheLoai_TabQLSanPham.setMinimumSize(new java.awt.Dimension(100, 25));
        cbbTheLoai_TabQLSanPham.setPreferredSize(new java.awt.Dimension(100, 25));
        cbbTheLoai_TabQLSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbTheLoai_TabQLSanPhamMouseClicked(evt);
            }
        });
        cbbTheLoai_TabQLSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTheLoai_TabQLSanPhamActionPerformed(evt);
            }
        });

        cbbNhaXuatBan_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbNhaXuatBan_TabQLSanPham.setMinimumSize(new java.awt.Dimension(100, 25));
        cbbNhaXuatBan_TabQLSanPham.setName(""); // NOI18N
        cbbNhaXuatBan_TabQLSanPham.setPreferredSize(new java.awt.Dimension(100, 25));
        cbbNhaXuatBan_TabQLSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbNhaXuatBan_TabQLSanPhamMouseClicked(evt);
            }
        });
        cbbNhaXuatBan_TabQLSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNhaXuatBan_TabQLSanPhamActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tên sách:");

        edtTenSach_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtTenSach_TabQLSanPham.setMinimumSize(new java.awt.Dimension(100, 25));
        edtTenSach_TabQLSanPham.setPreferredSize(new java.awt.Dimension(100, 25));
        jScrollPane3.setViewportView(edtTenSach_TabQLSanPham);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tác giả:");

        edtGiaBan_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtGiaBan_TabQLSanPham.setMinimumSize(new java.awt.Dimension(100, 25));
        edtGiaBan_TabQLSanPham.setPreferredSize(new java.awt.Dimension(100, 25));
        jScrollPane8.setViewportView(edtGiaBan_TabQLSanPham);

        edtTacGia_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane9.setViewportView(edtTacGia_TabQLSanPham);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Giá bán:");

        btnThem_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnThem_TabQLSanPham.setForeground(new java.awt.Color(0, 0, 102));
        btnThem_TabQLSanPham.setText("THÊM SÁCH");
        btnThem_TabQLSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_TabQLSanPhamActionPerformed(evt);
            }
        });

        btnSua_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnSua_TabQLSanPham.setForeground(new java.awt.Color(0, 0, 102));
        btnSua_TabQLSanPham.setText("SỬA SÁCH");
        btnSua_TabQLSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_TabQLSanPhamActionPerformed(evt);
            }
        });

        btnXoa_TabQLSanPham.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnXoa_TabQLSanPham.setForeground(new java.awt.Color(0, 0, 102));
        btnXoa_TabQLSanPham.setText("XÓA SÁCH");
        btnXoa_TabQLSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_TabQLSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12))
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbTheLoai_TabQLSanPham, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbNhaXuatBan_TabQLSanPham, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnChonAnh_TabQLSanPham)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlabelHinhAnh_TabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem_TabQLSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua_TabQLSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa_TabQLSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbbNhaXuatBan_TabQLSanPham, cbbTheLoai_TabQLSanPham, jScrollPane2, jScrollPane3, jScrollPane5, jScrollPane6, jScrollPane7, jScrollPane8, jScrollPane9});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTheLoai_TabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbNhaXuatBan_TabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlabelHinhAnh_TabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChonAnh_TabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnThem_TabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua_TabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa_TabQLSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSua_TabQLSanPham, btnThem_TabQLSanPham, btnXoa_TabQLSanPham});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbNhaXuatBan_TabQLSanPham, cbbTheLoai_TabQLSanPham, jScrollPane2, jScrollPane3, jScrollPane5, jScrollPane6, jScrollPane7, jScrollPane8});

        javax.swing.GroupLayout tabQLSanPhamLayout = new javax.swing.GroupLayout(tabQLSanPham);
        tabQLSanPham.setLayout(tabQLSanPhamLayout);
        tabQLSanPhamLayout.setHorizontalGroup(
            tabQLSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabQLSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabQLSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(471, Short.MAX_VALUE))
        );
        tabQLSanPhamLayout.setVerticalGroup(
            tabQLSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabQLSanPhamLayout.createSequentialGroup()
                .addGroup(tabQLSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabQLSanPhamLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabQLSanPhamLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(537, Short.MAX_VALUE))
        );

        tableTab.addTab("tab1", tabQLSanPham);

        tabNhapHang.setBackground(new java.awt.Color(0, 51, 102));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 255, 204));
        jLabel2.setText("Quản lý nhập hàng");

        jPanel5.setBackground(new java.awt.Color(0, 51, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo mới phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Mã nhân viên:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Mã phiếu nhập:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nhà cung cấp:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Ngày nhập:");

        btnTaoMoiPNH_TabQLNhapHang.setBackground(new java.awt.Color(153, 255, 255));
        btnTaoMoiPNH_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTaoMoiPNH_TabQLNhapHang.setForeground(new java.awt.Color(51, 0, 102));
        btnTaoMoiPNH_TabQLNhapHang.setText("Tạo mới");
        btnTaoMoiPNH_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoMoiPNH_TabQLNhapHangActionPerformed(evt);
            }
        });

        btnXoaPNH_TabQLNhapHang.setBackground(new java.awt.Color(153, 255, 255));
        btnXoaPNH_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnXoaPNH_TabQLNhapHang.setForeground(new java.awt.Color(51, 0, 102));
        btnXoaPNH_TabQLNhapHang.setText("Xóa");
        btnXoaPNH_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaPNH_TabQLNhapHangActionPerformed(evt);
            }
        });

        edtMaPhieuNhap_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtMaPhieuNhap_TabQLNhapHang.setEnabled(false);
        jScrollPane4.setViewportView(edtMaPhieuNhap_TabQLNhapHang);

        edtMaNhanVien_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane10.setViewportView(edtMaNhanVien_TabQLNhapHang);

        edtNgayNhap_TabQLNhapHang.setDateFormatString("dd/MM/yyyy");
        edtNgayNhap_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        cbbNhaCungCap_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel5)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbbNhaCungCap_TabQLNhapHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edtNgayNhap_TabQLNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnTaoMoiPNH_TabQLNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaPNH_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbbNhaCungCap_TabQLNhapHang, edtNgayNhap_TabQLNhapHang, jScrollPane10, jScrollPane4});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbbNhaCungCap_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(edtNgayNhap_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoMoiPNH_TabQLNhapHang)
                    .addComponent(btnXoaPNH_TabQLNhapHang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbNhaCungCap_TabQLNhapHang, edtNgayNhap_TabQLNhapHang, jScrollPane10, jScrollPane4});

        jPanel6.setBackground(new java.awt.Color(0, 51, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tbChiTietNhapHang_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tbChiTietNhapHang_TabQLNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbChiTietNhapHang_TabQLNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChiTietNhapHang_TabQLNhapHangMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tbChiTietNhapHang_TabQLNhapHang);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Thể loại sách:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Sách:");

        cbbTheLoaiSach_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTheLoaiSach_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTheLoaiSach_TabQLNhapHangActionPerformed(evt);
            }
        });

        cbbSach_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbSach_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSach_TabQLNhapHangActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Mã sách:");

        edtMaSach_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtMaSach_TabQLNhapHang.setEnabled(false);
        jScrollPane15.setViewportView(edtMaSach_TabQLNhapHang);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Số lượng:");

        edtSoLuong_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane16.setViewportView(edtSoLuong_TabQLNhapHang);

        btnThemChiTietNhapHang_TabQLNhapHang.setBackground(new java.awt.Color(153, 255, 255));
        btnThemChiTietNhapHang_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnThemChiTietNhapHang_TabQLNhapHang.setForeground(new java.awt.Color(51, 0, 102));
        btnThemChiTietNhapHang_TabQLNhapHang.setText("Thêm");
        btnThemChiTietNhapHang_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChiTietNhapHang_TabQLNhapHangActionPerformed(evt);
            }
        });

        btnSuaChiTietNhapHang_TabQLNhapHang.setBackground(new java.awt.Color(153, 255, 255));
        btnSuaChiTietNhapHang_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnSuaChiTietNhapHang_TabQLNhapHang.setForeground(new java.awt.Color(51, 0, 102));
        btnSuaChiTietNhapHang_TabQLNhapHang.setText("Sửa");
        btnSuaChiTietNhapHang_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaChiTietNhapHang_TabQLNhapHangActionPerformed(evt);
            }
        });

        btnXoaChiTietNhapHang_TabQLNhapHang.setBackground(new java.awt.Color(153, 255, 255));
        btnXoaChiTietNhapHang_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnXoaChiTietNhapHang_TabQLNhapHang.setForeground(new java.awt.Color(51, 0, 102));
        btnXoaChiTietNhapHang_TabQLNhapHang.setText("Xóa");
        btnXoaChiTietNhapHang_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaChiTietNhapHang_TabQLNhapHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnThemChiTietNhapHang_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnSuaChiTietNhapHang_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaChiTietNhapHang_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15)
                            .addComponent(cbbTheLoaiSach_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbSach_TabQLNhapHang, 0, 193, Short.MAX_VALUE)
                            .addComponent(jScrollPane16))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbbSach_TabQLNhapHang, cbbTheLoaiSach_TabQLNhapHang, jScrollPane15, jScrollPane16});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSuaChiTietNhapHang_TabQLNhapHang, btnThemChiTietNhapHang_TabQLNhapHang, btnXoaChiTietNhapHang_TabQLNhapHang});

        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel21)
                    .addComponent(cbbTheLoaiSach_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(cbbSach_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel23)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSuaChiTietNhapHang_TabQLNhapHang)
                    .addComponent(btnXoaChiTietNhapHang_TabQLNhapHang)
                    .addComponent(btnThemChiTietNhapHang_TabQLNhapHang))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbSach_TabQLNhapHang, cbbTheLoaiSach_TabQLNhapHang, jScrollPane15, jScrollPane16});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSuaChiTietNhapHang_TabQLNhapHang, btnThemChiTietNhapHang_TabQLNhapHang, btnXoaChiTietNhapHang_TabQLNhapHang});

        jPanel7.setBackground(new java.awt.Color(0, 51, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm phiếu nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Tìm theo:");

        cbbTimTheo_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTimTheo_TabQLNhapHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm tất cả", "Tìm theo ngày", "Tìm theo mã phiếu nhập", "Tìm theo nhà cung cấp", "Tìm theo nhân viên nhập" }));
        cbbTimTheo_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimTheo_TabQLNhapHangActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Nhập thông tin tìm kiếm:");

        edtThongTinTimKiem_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane14.setViewportView(edtThongTinTimKiem_TabQLNhapHang);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Kết quả:");

        cbbKetQuaTimKiem_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbKetQuaTimKiem_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKetQuaTimKiem_TabQLNhapHangActionPerformed(evt);
            }
        });

        btnTimKiem_TabQLNhapHang.setBackground(new java.awt.Color(153, 255, 255));
        btnTimKiem_TabQLNhapHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTimKiem_TabQLNhapHang.setForeground(new java.awt.Color(51, 0, 153));
        btnTimKiem_TabQLNhapHang.setText("Tìm kiếm");
        btnTimKiem_TabQLNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem_TabQLNhapHangActionPerformed(evt);
            }
        });

        edtNgayTimKiem_TabQLPhieuNhap.setDateFormatString("dd/MM/yyyy");
        edtNgayTimKiem_TabQLPhieuNhap.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(edtNgayTimKiem_TabQLPhieuNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(120, Short.MAX_VALUE))
                    .addComponent(jScrollPane14)
                    .addComponent(btnTimKiem_TabQLNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbKetQuaTimKiem_TabQLNhapHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(11, 11, 11)
                        .addComponent(cbbTimTheo_TabQLNhapHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbbTimTheo_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(edtNgayTimKiem_TabQLPhieuNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimKiem_TabQLNhapHang)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbKetQuaTimKiem_TabQLNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnTimKiem_TabQLNhapHang, cbbKetQuaTimKiem_TabQLNhapHang});

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {edtNgayTimKiem_TabQLPhieuNhap, jScrollPane14});

        javax.swing.GroupLayout tabNhapHangLayout = new javax.swing.GroupLayout(tabNhapHang);
        tabNhapHang.setLayout(tabNhapHangLayout);
        tabNhapHangLayout.setHorizontalGroup(
            tabNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabNhapHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabNhapHangLayout.createSequentialGroup()
                        .addGroup(tabNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap(495, Short.MAX_VALUE))
        );
        tabNhapHangLayout.setVerticalGroup(
            tabNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabNhapHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(tabNhapHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabNhapHangLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(572, Short.MAX_VALUE))
        );

        tableTab.addTab("tab4", tabNhapHang);

        tabBanHang.setBackground(new java.awt.Color(0, 51, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 255, 204));
        jLabel3.setText("Quản lý bán hàng");
        jLabel3.setMaximumSize(new java.awt.Dimension(226, 29));
        jLabel3.setMinimumSize(new java.awt.Dimension(226, 29));

        jPanel10.setBackground(new java.awt.Color(0, 51, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm phiếu bán hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Tìm theo nhân viên bán hàng:");

        cbbTimTheoNhanVien_TabQLBanHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Chọn thông tin tìm kiếm:");

        btnTimKiemTheoMaNV_TabQLBanHang.setBackground(new java.awt.Color(153, 255, 255));
        btnTimKiemTheoMaNV_TabQLBanHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTimKiemTheoMaNV_TabQLBanHang.setForeground(new java.awt.Color(51, 0, 153));
        btnTimKiemTheoMaNV_TabQLBanHang.setText("Tìm kiếm");
        btnTimKiemTheoMaNV_TabQLBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTheoMaNV_TabQLBanHangActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Tìm kiếm theo ngày:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Ngày bắt đầu:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Ngày kết thúc:");

        btnTimKiemTheoNgay_TabQLBanHang.setBackground(new java.awt.Color(153, 255, 255));
        btnTimKiemTheoNgay_TabQLBanHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTimKiemTheoNgay_TabQLBanHang.setForeground(new java.awt.Color(51, 0, 153));
        btnTimKiemTheoNgay_TabQLBanHang.setText("Tìm kiếm");
        btnTimKiemTheoNgay_TabQLBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemTheoNgay_TabQLBanHangActionPerformed(evt);
            }
        });

        btnXuatTatCa_TabQLBanHang.setBackground(new java.awt.Color(153, 255, 255));
        btnXuatTatCa_TabQLBanHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnXuatTatCa_TabQLBanHang.setForeground(new java.awt.Color(51, 0, 153));
        btnXuatTatCa_TabQLBanHang.setText("Xuất tất cả");
        btnXuatTatCa_TabQLBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatTatCa_TabQLBanHangActionPerformed(evt);
            }
        });

        edtNgayBatDau_TablQLBanHang.setDateFormatString("dd/MM/yyyy");
        edtNgayBatDau_TablQLBanHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        edtNgayKetThuc_TablQLBanHang.setDateFormatString("dd/MM/yyyy");
        edtNgayKetThuc_TablQLBanHang.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtNgayKetThuc_TablQLBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                            .addComponent(edtNgayBatDau_TablQLBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btnTimKiemTheoNgay_TabQLBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXuatTatCa_TabQLBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel25)
                    .addComponent(jLabel30)
                    .addComponent(cbbTimTheoNhanVien_TabQLBanHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiemTheoMaNV_TabQLBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNgayBatDau_TablQLBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel27)
                    .addComponent(edtNgayKetThuc_TablQLBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTimKiemTheoNgay_TabQLBanHang)
                .addGap(48, 48, 48)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTimTheoNhanVien_TabQLBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiemTheoMaNV_TabQLBanHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuatTatCa_TabQLBanHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(0, 51, 102));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phiếu bán hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tbDanhSachPhieuBan_TabQLBanHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbDanhSachPhieuBan_TabQLBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbDanhSachPhieuBan_TabQLBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachPhieuBan_TabQLBanHangMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(tbDanhSachPhieuBan_TabQLBanHang);

        btnXuatThongKeDoanhSoBanHang_TabQLBanHang.setBackground(new java.awt.Color(255, 0, 0));
        btnXuatThongKeDoanhSoBanHang_TabQLBanHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnXuatThongKeDoanhSoBanHang_TabQLBanHang.setForeground(new java.awt.Color(204, 255, 255));
        btnXuatThongKeDoanhSoBanHang_TabQLBanHang.setText("Xuất thống kê doanh số bán hàng");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXuatThongKeDoanhSoBanHang_TabQLBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane22))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane22, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatThongKeDoanhSoBanHang_TabQLBanHang)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 51, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chi tiết phiếu bán hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        tbChiTietPhieuBan_TabQLBanHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbChiTietPhieuBan_TabQLBanHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane17.setViewportView(tbChiTietPhieuBan_TabQLBanHang);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabBanHangLayout = new javax.swing.GroupLayout(tabBanHang);
        tabBanHang.setLayout(tabBanHangLayout);
        tabBanHangLayout.setHorizontalGroup(
            tabBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabBanHangLayout.createSequentialGroup()
                .addGroup(tabBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabBanHangLayout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(tabBanHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(467, Short.MAX_VALUE))
        );
        tabBanHangLayout.setVerticalGroup(
            tabBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabBanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabBanHangLayout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(525, Short.MAX_VALUE))
        );

        tableTab.addTab("tab4", tabBanHang);

        tabQLNhanVien.setBackground(new java.awt.Color(0, 51, 102));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Quản lý nhân viên");

        jPanel12.setBackground(new java.awt.Color(0, 51, 102));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        cbbTimKiemTheoLoai_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTimKiemTheoLoai_TabQLNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm tất cả", "Nhân viên bán hàng", "Nhân viên quản trị", "Tìm theo mã nhân viên" }));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Tìm kiếm theo loại:");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Chọn thông tin tìm kiếm:");

        btnTimKiem_TabQLNhanVien.setBackground(new java.awt.Color(153, 255, 255));
        btnTimKiem_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTimKiem_TabQLNhanVien.setForeground(new java.awt.Color(51, 0, 153));
        btnTimKiem_TabQLNhanVien.setText("Tìm kiếm");
        btnTimKiem_TabQLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiem_TabQLNhanVienActionPerformed(evt);
            }
        });

        cbbKetQua_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbKetQua_TabQLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbKetQua_TabQLNhanVienActionPerformed(evt);
            }
        });

        txt_data.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane29.setViewportView(txt_data);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Mã nhân viên:");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Danh sách nhân viên");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTimKiemTheoLoai_TabQLNhanVien, 0, 306, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(jLabel28)
                            .addComponent(jLabel46))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane29))
                    .addComponent(btnTimKiem_TabQLNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbKetQua_TabQLNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTimKiemTheoLoai_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel31)
                    .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTimKiem_TabQLNhanVien)
                .addGap(30, 30, 30)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbKetQua_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(0, 51, 102));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        labelHinhAnhNV_TabQLNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlycuahangbansach/Images/h1.png"))); // NOI18N
        labelHinhAnhNV_TabQLNhanVien.setText("jLabel31");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Mã nhân viên:");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Tên nhân viên:");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Giới tính:");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Ngày sinh:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Ngày vào làm:");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Số điện thoại:");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Chức vụ:");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Tên đăng nhập:");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Mật khẩu:");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Loại tài khoản:");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Quê quán:");

        edtMaNV_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        edtMaNV_TabQLNhanVien.setEnabled(false);
        jScrollPane20.setViewportView(edtMaNV_TabQLNhanVien);

        edtTenNV_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane21.setViewportView(edtTenNV_TabQLNhanVien);

        edtCCCD_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane23.setViewportView(edtCCCD_TabQLNhanVien);

        edtSoDienThoaiNV_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane27.setViewportView(edtSoDienThoaiNV_TabQLNhanVien);

        edtTenDangNhapNV_TabQlNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane28.setViewportView(edtTenDangNhapNV_TabQlNhanVien);

        edtQueQuan_TabNhaVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane32.setViewportView(edtQueQuan_TabNhaVien);

        btnThemNhaVien_TabQLNhanVien.setBackground(new java.awt.Color(153, 255, 255));
        btnThemNhaVien_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnThemNhaVien_TabQLNhanVien.setForeground(new java.awt.Color(255, 0, 0));
        btnThemNhaVien_TabQLNhanVien.setText("Thêm nhân viên");
        btnThemNhaVien_TabQLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhaVien_TabQLNhanVienActionPerformed(evt);
            }
        });

        btnSuaNV_TabQLNhanVien.setBackground(new java.awt.Color(153, 255, 255));
        btnSuaNV_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnSuaNV_TabQLNhanVien.setForeground(new java.awt.Color(255, 0, 0));
        btnSuaNV_TabQLNhanVien.setText("Sửa thông tin nhân viên");
        btnSuaNV_TabQLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNV_TabQLNhanVienActionPerformed(evt);
            }
        });

        btnXoaNV_tabQLNhanVien.setBackground(new java.awt.Color(153, 255, 255));
        btnXoaNV_tabQLNhanVien.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnXoaNV_tabQLNhanVien.setForeground(new java.awt.Color(255, 0, 0));
        btnXoaNV_tabQLNhanVien.setText("Khóa nhân viên");
        btnXoaNV_tabQLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNV_tabQLNhanVienActionPerformed(evt);
            }
        });

        cbbChucVuNV_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Số căn cước:");

        cbbGioiTinh_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbGioiTinh_TabQLNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Nam", "Nữ" }));

        cbbLoaiTaiKhoan_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbLoaiTaiKhoan_TabQLNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Tài khoản nhân viên", "Tài khoản quản lý" }));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Trạng thái");

        cbbTrangThai_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        cbbTrangThai_TabQLNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Đã khóa", "Đang mở" }));

        edtNgaySinh_TabQLNhanVien.setDateFormatString("dd/MM/yyyy");
        edtNgaySinh_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        edtNgayVaoLam_TabQLNhanVien.setDateFormatString("dd/MM/yyyy");
        edtNgayVaoLam_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        btnChonAnh_TabQLNhanVien.setBackground(new java.awt.Color(153, 255, 255));
        btnChonAnh_TabQLNhanVien.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        btnChonAnh_TabQLNhanVien.setForeground(new java.awt.Color(204, 0, 0));
        btnChonAnh_TabQLNhanVien.setText("Chọn ảnh");
        btnChonAnh_TabQLNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonAnh_TabQLNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel39)
                            .addComponent(jLabel34)
                            .addComponent(jLabel45)
                            .addComponent(jLabel35)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37)
                            .addComponent(jLabel38)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42)
                            .addComponent(jLabel47)
                            .addComponent(jLabel43))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                                    .addComponent(cbbChucVuNV_TabQLNhanVien, 0, 412, Short.MAX_VALUE)
                                    .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                                    .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                    .addComponent(cbbGioiTinh_TabQLNhanVien, 0, 256, Short.MAX_VALUE)
                                    .addComponent(edtNgaySinh_TabQLNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edtNgayVaoLam_TabQLNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane27, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                    .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                                    .addComponent(cbbLoaiTaiKhoan_TabQLNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbTrangThai_TabQLNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(edtMatKhauNV_TabQLNhanVien))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelHinhAnhNV_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnChonAnh_TabQLNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane32)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnThemNhaVien_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnSuaNV_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaNV_tabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbbChucVuNV_TabQLNhanVien, cbbGioiTinh_TabQLNhanVien, cbbLoaiTaiKhoan_TabQLNhanVien, cbbTrangThai_TabQLNhanVien, edtNgaySinh_TabQLNhanVien, edtNgayVaoLam_TabQLNhanVien, jScrollPane20, jScrollPane21, jScrollPane23, jScrollPane27, jScrollPane28});

        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(labelHinhAnhNV_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChonAnh_TabQLNhanVien))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel32)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel39)
                            .addComponent(cbbChucVuNV_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel34)
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel45)
                            .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel35)
                            .addComponent(cbbGioiTinh_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel36)
                            .addComponent(edtNgaySinh_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel37)
                            .addComponent(edtNgayVaoLam_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel38)
                            .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel40)
                            .addComponent(jScrollPane28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(edtMatKhauNV_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel42)
                            .addComponent(cbbLoaiTaiKhoan_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel47)
                            .addComponent(cbbTrangThai_TabQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel43)
                    .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemNhaVien_TabQLNhanVien)
                    .addComponent(btnSuaNV_TabQLNhanVien)
                    .addComponent(btnXoaNV_tabQLNhanVien))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbbChucVuNV_TabQLNhanVien, cbbGioiTinh_TabQLNhanVien, cbbLoaiTaiKhoan_TabQLNhanVien, cbbTrangThai_TabQLNhanVien, edtMatKhauNV_TabQLNhanVien, edtNgaySinh_TabQLNhanVien, edtNgayVaoLam_TabQLNhanVien, jScrollPane20, jScrollPane21, jScrollPane23, jScrollPane27, jScrollPane28});

        javax.swing.GroupLayout tabQLNhanVienLayout = new javax.swing.GroupLayout(tabQLNhanVien);
        tabQLNhanVien.setLayout(tabQLNhanVienLayout);
        tabQLNhanVienLayout.setHorizontalGroup(
            tabQLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabQLNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabQLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(tabQLNhanVienLayout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(475, Short.MAX_VALUE))
        );
        tabQLNhanVienLayout.setVerticalGroup(
            tabQLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabQLNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(tabQLNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(688, Short.MAX_VALUE))
        );

        tableTab.addTab("tab2", tabQLNhanVien);

        tabQLDoanhThu.setBackground(new java.awt.Color(0, 51, 102));

        btnLoadChart_TabQLDoanhThu.setText("Xem biểu đồ doanh thu");
        btnLoadChart_TabQLDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadChart_TabQLDoanhThuActionPerformed(evt);
            }
        });

        jPanel14.setBackground(new java.awt.Color(255, 0, 51));

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 204));
        jLabel48.setText("Số sách đã bán");

        lblSachDangDaBan_TabQLDanhThu.setBackground(new java.awt.Color(255, 0, 0));
        lblSachDangDaBan_TabQLDanhThu.setFont(new java.awt.Font("Segoe UI", 1, 150)); // NOI18N
        lblSachDangDaBan_TabQLDanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblSachDangDaBan_TabQLDanhThu.setText("100");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblSachDangDaBan_TabQLDanhThu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSachDangDaBan_TabQLDanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 0, 51));

        lblSachDangKD_TabQLDanhThu.setBackground(new java.awt.Color(255, 0, 0));
        lblSachDangKD_TabQLDanhThu.setFont(new java.awt.Font("Segoe UI", 1, 150)); // NOI18N
        lblSachDangKD_TabQLDanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblSachDangKD_TabQLDanhThu.setText("100");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 204));
        jLabel50.setText("Số sách hiện đang kinh doanh");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSachDangKD_TabQLDanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel50)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSachDangKD_TabQLDanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel50)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 0, 51));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 204));
        jLabel51.setText("Doanh thu tháng này");

        lblDoanhThuThang_TabQLDanhThu.setBackground(new java.awt.Color(255, 0, 0));
        lblDoanhThuThang_TabQLDanhThu.setFont(new java.awt.Font("Segoe UI", 1, 120)); // NOI18N
        lblDoanhThuThang_TabQLDanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblDoanhThuThang_TabQLDanhThu.setText("100");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblDoanhThuThang_TabQLDanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDoanhThuThang_TabQLDanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel51)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel17.setBackground(new java.awt.Color(255, 0, 51));

        lblSachDangDaBanTrongThang_TabQLDanhThu.setBackground(new java.awt.Color(255, 0, 0));
        lblSachDangDaBanTrongThang_TabQLDanhThu.setFont(new java.awt.Font("Segoe UI", 1, 150)); // NOI18N
        lblSachDangDaBanTrongThang_TabQLDanhThu.setForeground(new java.awt.Color(255, 255, 255));
        lblSachDangDaBanTrongThang_TabQLDanhThu.setText("100");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 204));
        jLabel53.setText("Số sách đã bán trong tháng");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSachDangDaBanTrongThang_TabQLDanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSachDangDaBanTrongThang_TabQLDanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel53)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabQLDoanhThuLayout = new javax.swing.GroupLayout(tabQLDoanhThu);
        tabQLDoanhThu.setLayout(tabQLDoanhThuLayout);
        tabQLDoanhThuLayout.setHorizontalGroup(
            tabQLDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabQLDoanhThuLayout.createSequentialGroup()
                .addGroup(tabQLDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tabQLDoanhThuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLoadChart_TabQLDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tabQLDoanhThuLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(tabQLDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(tabQLDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(532, Short.MAX_VALUE))
        );

        tabQLDoanhThuLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel14, jPanel16});

        tabQLDoanhThuLayout.setVerticalGroup(
            tabQLDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabQLDoanhThuLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(tabQLDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(tabQLDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(btnLoadChart_TabQLDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(579, Short.MAX_VALUE))
        );

        tableTab.addTab("tab3", tabQLDoanhThu);

        tabQLHoaDon.setBackground(new java.awt.Color(1, 1, 67));

        javax.swing.GroupLayout tabQLHoaDonLayout = new javax.swing.GroupLayout(tabQLHoaDon);
        tabQLHoaDon.setLayout(tabQLHoaDonLayout);
        tabQLHoaDonLayout.setHorizontalGroup(
            tabQLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1630, Short.MAX_VALUE)
        );
        tabQLHoaDonLayout.setVerticalGroup(
            tabQLHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1297, Short.MAX_VALUE)
        );

        tableTab.addTab("tab4", tabQLHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tableTab, javax.swing.GroupLayout.PREFERRED_SIZE, 1327, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, -40, 1630, 1250));

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));

        btnNhapHang.setBackground(new java.awt.Color(0, 0, 153));
        btnNhapHang.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnNhapHang.setForeground(new java.awt.Color(255, 255, 255));
        btnNhapHang.setText("NHẬP HÀNG");
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });

        btnBanHang.setBackground(new java.awt.Color(0, 0, 153));
        btnBanHang.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(255, 255, 255));
        btnBanHang.setText("PHIẾU BÁN HÀNG");
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnSanPham.setBackground(new java.awt.Color(0, 0, 153));
        btnSanPham.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnSanPham.setText("SẢN PHẨM");
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(0, 0, 153));
        btnNhanVien.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setText("NHÂN VIÊN");
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnThongKeDoanhThu.setBackground(new java.awt.Color(0, 0, 153));
        btnThongKeDoanhThu.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnThongKeDoanhThu.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKeDoanhThu.setText("THỐNG KÊ DOANH THU");
        btnThongKeDoanhThu.setPreferredSize(new java.awt.Dimension(313, 40));
        btnThongKeDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeDoanhThuActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(0, 0, 153));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setText("ĐĂNG XUẤT");
        jButton8.setPreferredSize(new java.awt.Dimension(313, 40));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNhapHang, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(btnBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(btnSanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(btnNhanVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(btnThongKeDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongKeDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(577, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBanHang, btnNhanVien, btnNhapHang, btnSanPham});

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 1210));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 295, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        // TODO add your handling code here:
        tableTab.setSelectedIndex(0);
        btnSanPham.setForeground(clickColor);
        btnNhanVien.setForeground(defaultColor);
        btnBanHang.setForeground(defaultColor);
        btnThongKeDoanhThu.setForeground(defaultColor);
        btnNhapHang.setForeground(defaultColor);
        LoadTabQLSanPhan();
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        // TODO add your handling code here:
        tableTab.setSelectedIndex(1);
        btnSanPham.setForeground(defaultColor);
        btnNhanVien.setForeground(defaultColor);
        btnBanHang.setForeground(defaultColor);
        btnThongKeDoanhThu.setForeground(defaultColor);
        btnNhapHang.setForeground(clickColor);
        edtNgayTimKiem_TabQLPhieuNhap.setVisible(false);
        edtThongTinTimKiem_TabQLNhapHang.setVisible(false);
        LoadComboBoxTheLoaiSach_TabQLNhapHang();
        LoadComboBoxNhaCungCap_TabQLNhapHang();
    }//GEN-LAST:event_btnNhapHangActionPerformed

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        // TODO add your handling code here:
        tableTab.setSelectedIndex(2);
        btnSanPham.setForeground(defaultColor);
        btnNhanVien.setForeground(defaultColor);
        btnBanHang.setForeground(clickColor);
        btnThongKeDoanhThu.setForeground(defaultColor);
        btnNhapHang.setForeground(defaultColor);
        LoadTableDanhSachPhieuBan_TabQLBanHang(new ArrayList<>());
        LoadtableChiTietPhieuBan_TabQLBanHang("");
        LoadComboBoxTimTheoNhanVien_TabQLBanHang();
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        // TODO add your handling code here:
        tableTab.setSelectedIndex(3);
        btnSanPham.setForeground(defaultColor);
        btnNhanVien.setForeground(clickColor);
        btnBanHang.setForeground(defaultColor);
        btnThongKeDoanhThu.setForeground(defaultColor);
        btnNhapHang.setForeground(defaultColor);
        LoadTabNhanVien();
        
    }//GEN-LAST:event_btnNhanVienActionPerformed
    private void LoadTabNhanVien()
    {
        ArrayList<Position> ds=PositionDAO.getAll();
        cbbChucVuNV_TabQLNhanVien.removeAllItems();
        cbbChucVuNV_TabQLNhanVien.addItem(new Position());
        for(Position p:ds)
        {
            cbbChucVuNV_TabQLNhanVien.addItem(p);
        }
    }
    
    
    private void btnThongKeDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeDoanhThuActionPerformed
        // TODO add your handling code here:
        tableTab.setSelectedIndex(4);
        btnSanPham.setForeground(defaultColor);
        btnNhanVien.setForeground(defaultColor);
        btnBanHang.setForeground(defaultColor);
        btnThongKeDoanhThu.setForeground(clickColor);
        btnNhapHang.setForeground(defaultColor);
        LoadTabQLThongKeDoanhThu();
    }//GEN-LAST:event_btnThongKeDoanhThuActionPerformed

    private void tb_SanPham_TabQLSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_SanPham_TabQLSanPhamMouseClicked
        // TODO add your handling code here:
        int row=tb_SanPham_TabQLSanPham.getSelectedRow();
        edtMaSach_TabQLSanPham.setText(String.valueOf(tb_SanPham_TabQLSanPham.getValueAt(row, 0)));
        String maNXB=String.valueOf(tb_SanPham_TabQLSanPham.getValueAt(row, 1));
        Publisher publisher=PublisherDAO.getOneById(maNXB);
        DefaultComboBoxModel<Publisher> cbbNXBModel=(DefaultComboBoxModel)cbbNhaXuatBan_TabQLSanPham.getModel();
        cbbNXBModel.setSelectedItem(publisher);
        Category categoryInput=CategoryDAO.getOneById(String.valueOf(tb_SanPham_TabQLSanPham.getValueAt(row, 2)));
        DefaultComboBoxModel<Category> cbbTheLoaiModel=(DefaultComboBoxModel)cbbTheLoai_TabQLSanPham.getModel();
        cbbTheLoaiModel.setSelectedItem(categoryInput);
        edtSerial_TabQLSanPham.setText(String.valueOf(tb_SanPham_TabQLSanPham.getValueAt(row, 3)));
        edtTenSach_TabQLSanPham.setText(String.valueOf(tb_SanPham_TabQLSanPham.getValueAt(row, 4)));
        edtSoLuongTon_TabQLSanPham.setText(String.valueOf(tb_SanPham_TabQLSanPham.getValueAt(row, 5)));
        edtSoLuongDanBan_TabQLSanPham.setText(String.valueOf(tb_SanPham_TabQLSanPham.getValueAt(row, 6)));
        
        
        String nameFile=tb_SanPham_TabQLSanPham.getModel().getValueAt(row, 9).toString();
        String pathIcon="Images/"+nameFile;
        ImageIcon icon = new ImageIcon(pathIcon);
            if(icon != null)
            {
                Image img = icon.getImage(); 
                Image newimg = img.getScaledInstance(jlabelHinhAnh_TabQLSanPham.getWidth(),
                        jlabelHinhAnh_TabQLSanPham.getHeight(),  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);
                jlabelHinhAnh_TabQLSanPham.setIcon(icon);
                jlabelHinhAnh_TabQLSanPham.setText(nameFile);
            }
        edtGiaBan_TabQLSanPham.setText(String.valueOf(tb_SanPham_TabQLSanPham.getValueAt(row, 8)));
        edtTacGia_TabQLSanPham.setText(String.valueOf(tb_SanPham_TabQLSanPham.getModel().getValueAt(row, 10)));
        
       
    }//GEN-LAST:event_tb_SanPham_TabQLSanPhamMouseClicked

    private void btnChonAnh_TabQLSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnh_TabQLSanPhamActionPerformed
        // TODO add your handling code here:
        JFileChooser jFileChooser=new JFileChooser("C:\\Users\\PC\\Documents\\NetBeansProjects");
        jFileChooser.setDialogTitle("Mở file");
        jFileChooser.showOpenDialog(null);
        File file=jFileChooser.getSelectedFile();
        imageUpLoadPath_SanPham=file;
        String path=file.getAbsolutePath();
        ImageUpLoad_TabQLSanPham=path.substring(path.lastIndexOf("\\")+1);
        if(path!=null)
        {
            ImageIcon icon = new ImageIcon(path);
            if(icon != null)
            {
                Image img = icon.getImage(); 
                Image newimg = img.getScaledInstance(jlabelHinhAnh_TabQLSanPham.getWidth(),
                        jlabelHinhAnh_TabQLSanPham.getHeight(),  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);
                jlabelHinhAnh_TabQLSanPham.setIcon(icon);
                jlabelHinhAnh_TabQLSanPham.setText(null);
            }
            jlabelHinhAnh_TabQLSanPham.setText(ImageUpLoad_TabQLSanPham);
            
            
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn ảnh");
        }
    }//GEN-LAST:event_btnChonAnh_TabQLSanPhamActionPerformed

    private void cbbTheLoai_TabQLSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTheLoai_TabQLSanPhamActionPerformed
        // TODO add your handling code here:
        Category category=(Category)cbbTheLoai_TabQLSanPham.getSelectedItem();
        if(category.getMaTheLoai().equals("addNew"))
        {
            InsertCategoryGui insertCategoryGui=new InsertCategoryGui();
            insertCategoryGui.show();
        }
       
    }//GEN-LAST:event_cbbTheLoai_TabQLSanPhamActionPerformed

    private void cbbNhaXuatBan_TabQLSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNhaXuatBan_TabQLSanPhamActionPerformed
        // TODO add your handling code here:
        Publisher nxb=(Publisher)cbbNhaXuatBan_TabQLSanPham.getSelectedItem();
        if(nxb.getMaNhaXuatBan().equals("addNew"))
        {
            InsertPublisherGUI pubisherGUI=new InsertPublisherGUI();
            pubisherGUI.show();
        }
    }//GEN-LAST:event_cbbNhaXuatBan_TabQLSanPhamActionPerformed

    private void btnThem_TabQLSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_TabQLSanPhamActionPerformed
        // TODO add your handling code here:
        String maSach=edtMaSach_TabQLSanPham.getText();
        String tenSach=edtTenSach_TabQLSanPham.getText();
        String serial=edtSerial_TabQLSanPham.getText();
        Category theLoai=(Category)cbbTheLoai_TabQLSanPham.getSelectedItem();
        Publisher nhaXuatBan=(Publisher)cbbNhaXuatBan_TabQLSanPham.getSelectedItem();
        int SoLuongTon=Integer.valueOf(edtSoLuongTon_TabQLSanPham.getText());
        int SoLuongDaBan=Integer.valueOf(edtSoLuongDanBan_TabQLSanPham.getText());
        String hinhAnh= jlabelHinhAnh_TabQLSanPham.getText();
        float giaBan= Float.valueOf(edtGiaBan_TabQLSanPham.getText());
        String tacGia=edtTacGia_TabQLSanPham.getText();
        Product s=new Product(maSach, nhaXuatBan.getMaNhaXuatBan(), theLoai.getMaTheLoai(),
                serial, tenSach, SoLuongTon, SoLuongDaBan, hinhAnh, giaBan, tacGia);
        int x=ProductDAO.insert(s.getMaNhaXuatBan(), s.getMaTheLoai(), s.getSerialSach(), s.getTenSach(), 
                s.getSoLuong(), s.getHinhAnh(), s.getGiaBan(), s.getTacGia());
        if(x>0)
        {
            JOptionPane.showMessageDialog(this, "Đã thêm sách thành công.");
            
            String fileName = ImageUpLoad_TabQLSanPham;
            String newPath = "Images/" + ImageUpLoad_TabQLSanPham;
            System.out.println(newPath);
            File newFile = new File(newPath);
            boolean success = imageUpLoadPath_SanPham.renameTo(newFile);
            if (success) {
                JOptionPane.showMessageDialog(this, "Đã lưu ảnh");
            }
            LoadTableSanPham_TabQLSanPham();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Đã thêm sách thất bại. Vui lòng thử lại");
        }
        
    }//GEN-LAST:event_btnThem_TabQLSanPhamActionPerformed

    private void btnSua_TabQLSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_TabQLSanPhamActionPerformed
        // TODO add your handling code here:
        
        String maSach=edtMaSach_TabQLSanPham.getText();
        String tenSach=edtTenSach_TabQLSanPham.getText();
        String serial=edtSerial_TabQLSanPham.getText();
        Category theLoai=(Category)cbbTheLoai_TabQLSanPham.getSelectedItem();
        Publisher nhaXuatBan=(Publisher)cbbNhaXuatBan_TabQLSanPham.getSelectedItem();
        int SoLuongTon=Integer.valueOf(edtSoLuongTon_TabQLSanPham.getText());
        int SoLuongDaBan=Integer.valueOf(edtSoLuongDanBan_TabQLSanPham.getText());
        String hinhAnh= jlabelHinhAnh_TabQLSanPham.getText();
        float giaBan= Float.valueOf(edtGiaBan_TabQLSanPham.getText());
        String tacGia=edtTacGia_TabQLSanPham.getText();
        Product s=new Product(maSach, nhaXuatBan.getMaNhaXuatBan(), theLoai.getMaTheLoai(),
                serial, tenSach, SoLuongTon, SoLuongDaBan, hinhAnh, giaBan, tacGia);
        int x=ProductDAO.upDate(s.getMaSach(), s.getMaNhaXuatBan(), s.getMaTheLoai(), s.getSerialSach(), s.getTenSach(), 
                s.getSoLuong(), s.getHinhAnh(), s.getGiaBan(), s.getTacGia());
        if(x>0)
        {
            JOptionPane.showMessageDialog(this, "Đã sửa sách thành công.");
            String fileName = ImageUpLoad_TabQLSanPham;
            String newPath = "Images/" + ImageUpLoad_TabQLSanPham;
            System.out.println(newPath);
            File newFile = new File(newPath);
            boolean success = imageUpLoadPath_SanPham.renameTo(newFile);
            if (success) {
                JOptionPane.showMessageDialog(this, "Đã lưu ảnh");
            }
            LoadTableSanPham_TabQLSanPham();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Sửa sách thất bại. Vui lòng thử lại");
        }
    }//GEN-LAST:event_btnSua_TabQLSanPhamActionPerformed

    private void btnTimKiem_TabQLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem_TabQLNhanVienActionPerformed
        // TODO add your handling code here:
        int index=cbbTimKiemTheoLoai_TabQLNhanVien.getSelectedIndex();
        if(index==0)
        {
            cbbKetQua_TabQLNhanVien.removeAllItems();
            ArrayList<SalePerson> danhSachNhanVien=SalesPersonDAO.getAll();
            for(SalePerson s:danhSachNhanVien)
            {
                cbbKetQua_TabQLNhanVien.addItem(s);
            }
        }
        if(index==1)
        {
            cbbKetQua_TabQLNhanVien.removeAllItems();
            ArrayList<SalePerson> danhSachNhanVien=SalesPersonDAO.getListSalesPersonByCategory("NVBH");
            for(SalePerson s:danhSachNhanVien)
            {
                cbbKetQua_TabQLNhanVien.addItem(s);
            }
            
        }
        if(index==2)
        {
            cbbKetQua_TabQLNhanVien.removeAllItems();
            ArrayList<SalePerson> danhSachNhanVien=SalesPersonDAO.getListSalesPersonByCategory("NVQL");
            for(SalePerson s:danhSachNhanVien)
            {
                cbbKetQua_TabQLNhanVien.addItem(s);
            }
        }
        if(index==3)
        {
            String maNhanVien=txt_data.getText();
            cbbKetQua_TabQLNhanVien.removeAllItems();
            SalePerson p=SalesPersonDAO.getOneSalePersonByIDSalePerson(maNhanVien);
            if(p!=null)
            {
                try {
                    DefaultComboBoxModel<Position> cbbChucVuModel= (DefaultComboBoxModel) cbbChucVuNV_TabQLNhanVien.getModel();
                    Position s=PositionDAO.getOnePositionByID(p.getMaChucVu());
                    edtMaNV_TabQLNhanVien.setText(p.getMaNhanVien());
                    cbbChucVuModel.setSelectedItem(s);
                    edtTenNV_TabQLNhanVien.setText(p.getTenNhanVien());
                    edtCCCD_TabQLNhanVien.setText(String.valueOf(p.getCanCuocCongDan()));
                    cbbGioiTinh_TabQLNhanVien.setSelectedItem(p.getGioiTinh());
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                    if(!p.getNgaySinh().equals("")&& p.getNgaySinh()!=null)
                    {
                        Date NgaySinh = new Date(0);
                        NgaySinh= (Date) dt.parse(p.getNgaySinh());
                        edtNgaySinh_TabQLNhanVien.setDate(NgaySinh);
                    }
                    if(!p.getNgayVaoLam().equals("")&& p.getNgayVaoLam()!=null)
                    {
                        Date NgayVaoLam = new Date(0);
                        NgayVaoLam= (Date) dt.parse(p.getNgayVaoLam());
                        edtNgayVaoLam_TabQLNhanVien.setDate(NgayVaoLam);
                    }
                    
                    
                    edtSoDienThoaiNV_TabQLNhanVien.setText(String.valueOf(p.getSdt()) );
                    edtTenDangNhapNV_TabQlNhanVien.setText(p.getTenDangNhap());
                    edtMatKhauNV_TabQLNhanVien.setText(p.getMatKhau());
                    cbbLoaiTaiKhoan_TabQLNhanVien.setSelectedItem(p.getLoaiTaiKhoan());
                    cbbTrangThai_TabQLNhanVien.setSelectedItem(p.getTrangThai());
                    edtQueQuan_TabNhaVien.setText(p.getDiaChi());
                    ImageIcon icon = new ImageIcon("Images/"+p.getHinhAnh());
                    Image image = icon.getImage(); // transform it
                    Image newImage = image.getScaledInstance(240, 360,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    icon = new ImageIcon(newImage);
                    labelHinhAnhNV_TabQLNhanVien.setIcon(icon);
                    labelHinhAnhNV_TabQLNhanVien.setText("");
                } catch (ParseException ex) {
                    Logger.getLogger(AdminPageGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }//GEN-LAST:event_btnTimKiem_TabQLNhanVienActionPerformed

    private void cbbKetQua_TabQLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKetQua_TabQLNhanVienActionPerformed
        // TODO add your handling code here:
        SalePerson p=(SalePerson)cbbKetQua_TabQLNhanVien.getSelectedItem();
            if(p!=null)
            {
                try {
                    DefaultComboBoxModel<Position> cbbChucVuModel= (DefaultComboBoxModel) cbbChucVuNV_TabQLNhanVien.getModel();
                    Position s=PositionDAO.getOnePositionByID(p.getMaChucVu());
                    edtMaNV_TabQLNhanVien.setText(p.getMaNhanVien());
                    cbbChucVuModel.setSelectedItem(s);
                    edtTenNV_TabQLNhanVien.setText(p.getTenNhanVien());
                    edtCCCD_TabQLNhanVien.setText(String.valueOf(p.getCanCuocCongDan()));
                    cbbGioiTinh_TabQLNhanVien.setSelectedItem(p.getGioiTinh());
                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                    if(!p.getNgaySinh().equals("")&& p.getNgaySinh()!=null)
                    {
                        Date NgaySinh = new Date(0);
                        NgaySinh= (Date) dt.parse(p.getNgaySinh());
                        edtNgaySinh_TabQLNhanVien.setDate(NgaySinh);
                    }
                    if(!p.getNgayVaoLam().equals("")&& p.getNgayVaoLam()!=null)
                    {
                        Date NgayVaoLam = new Date(0);
                        NgayVaoLam= (Date) dt.parse(p.getNgayVaoLam());
                        edtNgayVaoLam_TabQLNhanVien.setDate(NgayVaoLam);
                    }
                    
                    
                    edtSoDienThoaiNV_TabQLNhanVien.setText(String.valueOf(p.getSdt()) );
                    edtTenDangNhapNV_TabQlNhanVien.setText(p.getTenDangNhap());
                    edtMatKhauNV_TabQLNhanVien.setText(p.getMatKhau());
                    cbbLoaiTaiKhoan_TabQLNhanVien.setSelectedItem(p.getLoaiTaiKhoan());
                    cbbTrangThai_TabQLNhanVien.setSelectedItem(p.getTrangThai());
                    edtQueQuan_TabNhaVien.setText(p.getDiaChi());
                    ImageIcon icon = new ImageIcon("Images/"+p.getHinhAnh());
                    Image image = icon.getImage(); // transform it
                    Image newImage = image.getScaledInstance(240, 360,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    icon = new ImageIcon(newImage);
                    labelHinhAnhNV_TabQLNhanVien.setIcon(icon);
                    labelHinhAnhNV_TabQLNhanVien.setText("");
                } catch (ParseException ex) {
                    Logger.getLogger(AdminPageGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
    }//GEN-LAST:event_cbbKetQua_TabQLNhanVienActionPerformed

    private void btnThemNhaVien_TabQLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhaVien_TabQLNhanVienActionPerformed
        // TODO add your handling code here:
        Position maChucVu = (Position)cbbChucVuNV_TabQLNhanVien.getSelectedItem();
        String hovaTen = edtTenNV_TabQLNhanVien.getText();
        String CCCD=edtCCCD_TabQLNhanVien.getText();
        String gioiTinh = cbbGioiTinh_TabQLNhanVien.getSelectedItem().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ngaySinh =formatter.format(edtNgaySinh_TabQLNhanVien.getDate());
        String ngayVaoLam =formatter.format(edtNgayVaoLam_TabQLNhanVien.getDate());
        String SDT =  edtSoDienThoaiNV_TabQLNhanVien.getText();
        String tenDangNhap = edtTenDangNhapNV_TabQlNhanVien.getText();
        String matKhau = edtMatKhauNV_TabQLNhanVien.getText();
        String diaChi = edtQueQuan_TabNhaVien.getText();
        String loaiTaiKhoan = cbbLoaiTaiKhoan_TabQLNhanVien.getSelectedItem().toString();
        String hinhAnh = labelHinhAnhNV_TabQLNhanVien.getText();
        String trangThai = cbbTrangThai_TabQLNhanVien.getSelectedItem().toString();
        
        SalePerson sp = new SalePerson();
        sp.setTenNhanVien(hovaTen);
        sp.setNgaySinh(ngaySinh);
        sp.setNgayVaoLam(ngayVaoLam);
        sp.setGioiTinh(gioiTinh);
        sp.setMaChucVu(maChucVu.getMaChucVu());
        sp.setCanCuocCongDan(CCCD);
        sp.setSdt(SDT);
        sp.setDiaChi(diaChi);
        sp.setTenDangNhap(tenDangNhap);
        sp.setMatKhau(matKhau);
        sp.setLoaiTaiKhoan(loaiTaiKhoan);
        sp.setTrangThai(trangThai);
        sp.setHinhAnh(hinhAnh);
        int r = SalesPersonDAO.insertOneProfie(sp);
        
        if(r > 0 ) {
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
            String fileName = sp.getHinhAnh();
            String newPath = "Images/" + labelHinhAnhNV_TabQLNhanVien.getText();
            System.out.println(newPath);
            File newFile = new File(newPath);
            boolean success = imageUpLoadPath_NhanVien.renameTo(newFile);
            if (success) {
                ImageIcon imageIcon = new ImageIcon(newPath);
                labelHinhAnhNV_TabQLNhanVien.setIcon(imageIcon);
                JOptionPane.showMessageDialog(this, "Đã lưu ảnh");
            }
        }
        else {
             JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!");
        }
    }//GEN-LAST:event_btnThemNhaVien_TabQLNhanVienActionPerformed

    private void btnChonAnh_TabQLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonAnh_TabQLNhanVienActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
        JFileChooser jFileChooser=new JFileChooser("C:\\Users\\PC\\Documents\\NetBeansProjects");
        jFileChooser.setDialogTitle("Mở file");
        jFileChooser.showOpenDialog(null);
        File file=jFileChooser.getSelectedFile();
        imageUpLoadPath_NhanVien=file;
        String path=file.getAbsolutePath();
        ImageUpLoad_TabQLNhanVien=path.substring(path.lastIndexOf("\\")+1);
        if(path!=null)
        {
            ImageIcon icon = new ImageIcon(path);
            if(icon != null)
            {
                Image img = icon.getImage(); 
                Image newimg = img.getScaledInstance(labelHinhAnhNV_TabQLNhanVien.getWidth(),
                        labelHinhAnhNV_TabQLNhanVien.getHeight(),  java.awt.Image.SCALE_SMOOTH);
                icon = new ImageIcon(newimg);
                labelHinhAnhNV_TabQLNhanVien.setIcon(icon);
                labelHinhAnhNV_TabQLNhanVien.setText(null);
            }
            labelHinhAnhNV_TabQLNhanVien.setText(ImageUpLoad_TabQLNhanVien);
            System.out.println(labelHinhAnhNV_TabQLNhanVien.getText());
            
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Bạn chưa chọn ảnh");
        }
    }//GEN-LAST:event_btnChonAnh_TabQLNhanVienActionPerformed

    private void btnSuaNV_TabQLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNV_TabQLNhanVienActionPerformed
        // TODO add your handling code here:
        String maNhanVien=edtMaNV_TabQLNhanVien.getText();
         Position maChucVu = (Position)cbbChucVuNV_TabQLNhanVien.getSelectedItem();
        String hovaTen = edtTenNV_TabQLNhanVien.getText();
        String CCCD=edtCCCD_TabQLNhanVien.getText();
        String gioiTinh = cbbGioiTinh_TabQLNhanVien.getSelectedItem().toString();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ngaySinh =formatter.format(edtNgaySinh_TabQLNhanVien.getDate());
        String ngayVaoLam =formatter.format(edtNgayVaoLam_TabQLNhanVien.getDate());
        String SDT =  edtSoDienThoaiNV_TabQLNhanVien.getText();
        String tenDangNhap = edtTenDangNhapNV_TabQlNhanVien.getText();
        String matKhau = edtMatKhauNV_TabQLNhanVien.getText();
        String diaChi = edtQueQuan_TabNhaVien.getText();
        String loaiTaiKhoan = cbbLoaiTaiKhoan_TabQLNhanVien.getSelectedItem().toString();
        String hinhAnh = labelHinhAnhNV_TabQLNhanVien.getText();
        String trangThai = cbbTrangThai_TabQLNhanVien.getSelectedItem().toString();
        
        SalePerson sp = new SalePerson();
        sp.setMaNhanVien(maNhanVien);
        sp.setTenNhanVien(hovaTen);
        sp.setNgaySinh(ngaySinh);
        sp.setNgayVaoLam(ngayVaoLam);
        sp.setGioiTinh(gioiTinh);
        sp.setMaChucVu(maChucVu.getMaChucVu());
        sp.setCanCuocCongDan(CCCD);
        sp.setSdt(SDT);
        sp.setDiaChi(diaChi);
        sp.setTenDangNhap(tenDangNhap);
        sp.setMatKhau(matKhau);
        sp.setLoaiTaiKhoan(loaiTaiKhoan);
        sp.setTrangThai(trangThai);
        sp.setHinhAnh(hinhAnh);
        int r = SalesPersonDAO.UpdateProfie(sp);
        
        if(r > 0 ) {
            JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công!");
           

            String fileName = sp.getHinhAnh();
            String newPath = "Images/" + labelHinhAnhNV_TabQLNhanVien.getText();
            System.out.println(newPath);
            File newFile = new File(newPath);
            boolean success = imageUpLoadPath_NhanVien.renameTo(newFile);
            if (success) {
                ImageIcon imageIcon = new ImageIcon(newPath);
                labelHinhAnhNV_TabQLNhanVien.setIcon(imageIcon);
                JOptionPane.showMessageDialog(this, "Đã lưu ảnh");
            }
            
            
        }
        else {
             JOptionPane.showMessageDialog(this, "Sửa nhân viên thất bại!");
        }
    }//GEN-LAST:event_btnSuaNV_TabQLNhanVienActionPerformed

    private void btnXoaNV_tabQLNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNV_tabQLNhanVienActionPerformed
        // TODO add your handling code here:
        if(SalesPersonDAO.UpdateProfie_Block(edtMaNV_TabQLNhanVien.getText())>0)
        {
            JOptionPane.showMessageDialog(this, "Đã khóa");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Lỗi.");
        }
        
    }//GEN-LAST:event_btnXoaNV_tabQLNhanVienActionPerformed

    private void btnTaoMoiPNH_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoMoiPNH_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        GoodsDeliveryNote g=new GoodsDeliveryNote();
        g.setMaNhanVien(s.getMaNhanVien());
        Supplier supp=(Supplier)cbbNhaCungCap_TabQLNhapHang.getSelectedItem();
        g.setMaNCC(supp.getMaNhaCungCap());
        int kq=0;
        kq=GoodsDeliveryNoteDAO.insertOneGoodsDeliveryNote(g);
        if(kq > 0 ) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            GoodsDeliveryNote phieuNhapMoiNhat=GoodsDeliveryNoteDAO.getLastGoodsDeliveryNoteByID(this.s.getMaNhanVien());
            if(phieuNhapMoiNhat!=null)
            {
                edtMaPhieuNhap_TabQLNhapHang.setText(phieuNhapMoiNhat.getMaPhieuNhap());
                edtMaNhanVien_TabQLNhapHang.setText(phieuNhapMoiNhat.getMaNhanVien());
                
            }
        }
        else {
             JOptionPane.showMessageDialog(this, "Lỗi.");
        }
        
    }//GEN-LAST:event_btnTaoMoiPNH_TabQLNhapHangActionPerformed

    private void btnTimKiem_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiem_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        int index = cbbTimTheo_TabQLNhapHang.getSelectedIndex();
        if(index==0)
        {
            cbbKetQuaTimKiem_TabQLNhapHang.removeAllItems();
            ArrayList<GoodsDeliveryNote> values=GoodsDeliveryNoteDAO.getAll();
            for(GoodsDeliveryNote g:values)
            {
                cbbKetQuaTimKiem_TabQLNhapHang.addItem(g);
            }
            
        }
        else if(index==1)
        {
            cbbKetQuaTimKiem_TabQLNhapHang.removeAllItems();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String ngay =formatter.format(edtNgayTimKiem_TabQLPhieuNhap.getDate());
            ArrayList<GoodsDeliveryNote> values=GoodsDeliveryNoteDAO.getListByDateValue(ngay);
            for(GoodsDeliveryNote g:values)
            {
                cbbKetQuaTimKiem_TabQLNhapHang.addItem(g);
            }
        }
        else if(index==2)
        {
            cbbKetQuaTimKiem_TabQLNhapHang.removeAllItems();
            String maPhieuNhap=edtThongTinTimKiem_TabQLNhapHang.getText();
            GoodsDeliveryNote values=GoodsDeliveryNoteDAO.getListByID(maPhieuNhap);
            if(values!=null)
            {
                cbbKetQuaTimKiem_TabQLNhapHang.addItem(values);
                edtMaPhieuNhap_TabQLNhapHang.setText(values.getMaPhieuNhap());
                edtMaNhanVien_TabQLNhapHang.setText(values.getMaNhanVien());
                
                DefaultComboBoxModel dcm=(DefaultComboBoxModel)cbbNhaCungCap_TabQLNhapHang.getModel();
                Supplier nhaCungCap=SupplierDAO.getOneByID(values.getMaNCC());
                if(nhaCungCap!=null)
                {
                    dcm.setSelectedItem(nhaCungCap);
                }
                SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
                if(!values.getNgayNhap().equals("")&& values.getNgayNhap()!=null)
                {
                    Date NgaySinh = new Date(0);
                    try {
                        NgaySinh= (Date) dt.parse(values.getNgayNhap());
                    } catch (ParseException ex) {
                        Logger.getLogger(AdminPageGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    edtNgayNhap_TabQLNhapHang.setDate(NgaySinh);
                }
                ArrayList<GoodsReceivedNote> dsChiTietPhieuNhap=  GoodsReceivedNoteDAO.getAll(values.getMaPhieuNhap());
                LoadTableChiTietNhapHang_TabQLNhapHang(dsChiTietPhieuNhap);
            }
            
        }
        else if(index==3)
        {
            cbbKetQuaTimKiem_TabQLNhapHang.removeAllItems();
            String maNhaCungCap=edtThongTinTimKiem_TabQLNhapHang.getText();
            ArrayList<GoodsDeliveryNote> values=GoodsDeliveryNoteDAO.getListByIDSupplierValue(maNhaCungCap);
            for(GoodsDeliveryNote g:values)
            {
                cbbKetQuaTimKiem_TabQLNhapHang.addItem(g);
            }
        }
        else if(index==4)
        {
            cbbKetQuaTimKiem_TabQLNhapHang.removeAllItems();
            String maNhanVien=edtThongTinTimKiem_TabQLNhapHang.getText();
            ArrayList<GoodsDeliveryNote> values=GoodsDeliveryNoteDAO.getListByIDSalePersonValue(maNhanVien);
            for(GoodsDeliveryNote g:values)
            {
                cbbKetQuaTimKiem_TabQLNhapHang.addItem(g);
            }
        }
    }//GEN-LAST:event_btnTimKiem_TabQLNhapHangActionPerformed

    private void cbbTimTheo_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimTheo_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        if(cbbTimTheo_TabQLNhapHang.getSelectedIndex()==1)
        {
            edtNgayTimKiem_TabQLPhieuNhap.setVisible(true);
            edtThongTinTimKiem_TabQLNhapHang.setVisible(false);
        }
        else
        {
            edtNgayTimKiem_TabQLPhieuNhap.setVisible(false);
            edtThongTinTimKiem_TabQLNhapHang.setVisible(true);
        }
    }//GEN-LAST:event_cbbTimTheo_TabQLNhapHangActionPerformed

    private void cbbKetQuaTimKiem_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbKetQuaTimKiem_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        GoodsDeliveryNote phieuNhapHang=(GoodsDeliveryNote)cbbKetQuaTimKiem_TabQLNhapHang.getSelectedItem();
        if(phieuNhapHang!=null)
        {
            
            edtMaPhieuNhap_TabQLNhapHang.setText(phieuNhapHang.getMaPhieuNhap());
            edtMaNhanVien_TabQLNhapHang.setText(phieuNhapHang.getMaNhanVien());
            DefaultComboBoxModel dcm=(DefaultComboBoxModel)cbbNhaCungCap_TabQLNhapHang.getModel();
            Supplier nhaCungCap=SupplierDAO.getOneByID(phieuNhapHang.getMaNCC());
            if(nhaCungCap!=null)
            {
                dcm.setSelectedItem(nhaCungCap);
            }
            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
            if(!phieuNhapHang.getNgayNhap().equals("")&& phieuNhapHang.getNgayNhap()!=null)
            {
                Date NgaySinh = new Date(0);
                try {
                    NgaySinh= (Date) dt.parse(phieuNhapHang.getNgayNhap());
                } catch (ParseException ex) {
                    Logger.getLogger(AdminPageGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                edtNgayNhap_TabQLNhapHang.setDate(NgaySinh);
            }
            ArrayList<GoodsReceivedNote> dsChiTietPhieuNhap=  GoodsReceivedNoteDAO.getAll(phieuNhapHang.getMaPhieuNhap());
            LoadTableChiTietNhapHang_TabQLNhapHang(dsChiTietPhieuNhap);
        }
    }//GEN-LAST:event_cbbKetQuaTimKiem_TabQLNhapHangActionPerformed

    private void cbbTheLoaiSach_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTheLoaiSach_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        Category c=(Category)cbbTheLoaiSach_TabQLNhapHang.getSelectedItem();
        if(c!=null)
        {
            LoadComboBoxSach_TabQLNhapHang(c.getMaTheLoai());
        }
        
    }//GEN-LAST:event_cbbTheLoaiSach_TabQLNhapHangActionPerformed

    private void cbbSach_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSach_TabQLNhapHangActionPerformed
        //TODO add your handling code here:
        DefaultComboBoxModel dcm=(DefaultComboBoxModel) cbbSach_TabQLNhapHang.getModel();
        Product p =(Product)dcm.getSelectedItem();
        if(p!=null)
        {
            edtMaSach_TabQLNhapHang.setText(p.getMaSach());
        }
        else
        {
            edtMaSach_TabQLNhapHang.setText("");
        }
        
    }//GEN-LAST:event_cbbSach_TabQLNhapHangActionPerformed

    private void tbChiTietNhapHang_TabQLNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChiTietNhapHang_TabQLNhapHangMouseClicked
        // TODO add your handling code here:
        int row=tbChiTietNhapHang_TabQLNhapHang.getSelectedRow();
        if(row>=0)
        {
            edtMaSach_TabQLNhapHang.setText(String.valueOf(tbChiTietNhapHang_TabQLNhapHang.getValueAt(row, 2)));
            edtSoLuong_TabQLNhapHang.setText(String.valueOf(tbChiTietNhapHang_TabQLNhapHang.getValueAt(row, 4)));
        }
    }//GEN-LAST:event_tbChiTietNhapHang_TabQLNhapHangMouseClicked

    private void btnThemChiTietNhapHang_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChiTietNhapHang_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        GoodsReceivedNote chiTietPhieuNhap=new GoodsReceivedNote();
        chiTietPhieuNhap.setMaPhieuNhap(edtMaPhieuNhap_TabQLNhapHang.getText());
        chiTietPhieuNhap.setMaSach(edtMaSach_TabQLNhapHang.getText());
        chiTietPhieuNhap.setSoLuong(Integer.valueOf(edtSoLuong_TabQLNhapHang.getText()));
        int kq=0;
        kq=GoodsReceivedNoteDAO.insertOneGoodsReceivedNode(chiTietPhieuNhap);
        if(kq>0)
        {
            JOptionPane.showMessageDialog(this, "Thêm thành công.");
            ArrayList<GoodsReceivedNote> dsChiTietPhieuNhap=GoodsReceivedNoteDAO.getAll(chiTietPhieuNhap.getMaPhieuNhap());
            LoadTableChiTietNhapHang_TabQLNhapHang(dsChiTietPhieuNhap);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Lỗi.");
        }
    }//GEN-LAST:event_btnThemChiTietNhapHang_TabQLNhapHangActionPerformed

    private void btnSuaChiTietNhapHang_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaChiTietNhapHang_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        GoodsReceivedNote chiTietPhieuNhap=new GoodsReceivedNote();
        chiTietPhieuNhap.setMaPhieuNhap(edtMaPhieuNhap_TabQLNhapHang.getText());
        chiTietPhieuNhap.setMaSach(edtMaSach_TabQLNhapHang.getText());
        chiTietPhieuNhap.setSoLuong(Integer.valueOf(edtSoLuong_TabQLNhapHang.getText()));
        int kq=0;
        kq=GoodsReceivedNoteDAO.updateOneGoodsReceivedNode(chiTietPhieuNhap);
        if(kq>0)
        {
            JOptionPane.showMessageDialog(this, "Sửa thành công.");
            ArrayList<GoodsReceivedNote> dsChiTietPhieuNhap=GoodsReceivedNoteDAO.getAll(chiTietPhieuNhap.getMaPhieuNhap());
            LoadTableChiTietNhapHang_TabQLNhapHang(dsChiTietPhieuNhap);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Lỗi.");
        }
    }//GEN-LAST:event_btnSuaChiTietNhapHang_TabQLNhapHangActionPerformed

    private void btnXoaChiTietNhapHang_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaChiTietNhapHang_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        GoodsReceivedNote chiTietPhieuNhap=new GoodsReceivedNote();
        chiTietPhieuNhap.setMaPhieuNhap(edtMaPhieuNhap_TabQLNhapHang.getText());
        chiTietPhieuNhap.setMaSach(edtMaSach_TabQLNhapHang.getText());
        chiTietPhieuNhap.setSoLuong(Integer.valueOf(edtSoLuong_TabQLNhapHang.getText()));
        int kq=0;
        int n=JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa chi tiết phiếu nhập này?","Lưu ý",
                JOptionPane.YES_NO_OPTION);
        if(n==JOptionPane.YES_OPTION)
        {
            kq=GoodsReceivedNoteDAO.deleteOneGoodsReceivedNode(chiTietPhieuNhap);
        if(kq>0)
        {
            JOptionPane.showMessageDialog(this, "Xóa thành công.");
            ArrayList<GoodsReceivedNote> dsChiTietPhieuNhap=GoodsReceivedNoteDAO.getAll(chiTietPhieuNhap.getMaPhieuNhap());
            LoadTableChiTietNhapHang_TabQLNhapHang(dsChiTietPhieuNhap);
            clearChiTietNhapHang_TabQLNhapHang();
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Lỗi.");
        }
        }
        
    }//GEN-LAST:event_btnXoaChiTietNhapHang_TabQLNhapHangActionPerformed

    private void btnXoaPNH_TabQLNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaPNH_TabQLNhapHangActionPerformed
        // TODO add your handling code here:
        GoodsDeliveryNote g=new GoodsDeliveryNote();
        g.setMaNhanVien(s.getMaNhanVien());
        Supplier s=(Supplier)cbbNhaCungCap_TabQLNhapHang.getSelectedItem();
        g.setMaNCC(s.getMaNhaCungCap());
        g.setMaPhieuNhap(edtMaPhieuNhap_TabQLNhapHang.getText());
        int kq=0;
        
        int n=JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa phiếu nhập này?\n "+
                "Mọi chi tiết phiếu nhập thuộc phiếu này sẽ bị xóa tất cả.","Lưu ý",JOptionPane.YES_NO_OPTION);
        if(n==JOptionPane.YES_OPTION)
        {
            kq=GoodsReceivedNoteDAO.deleteListGoodsReceivedNoteByIDGoodsDeliveryNote(g.getMaPhieuNhap());
            kq=GoodsDeliveryNoteDAO.deleteOneGoodsDeliveryNote(g);
            if(kq>0)
            {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Lỗi.");
            }
        }
        
    }//GEN-LAST:event_btnXoaPNH_TabQLNhapHangActionPerformed

    private void btnTimKiemTheoNgay_TabQLBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTheoNgay_TabQLBanHangActionPerformed
        // TODO add your handling code here:
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ngayBatDau =formatter.format(edtNgayBatDau_TablQLBanHang.getDate());
        String ngayKetThuc=formatter.format(edtNgayKetThuc_TablQLBanHang.getDate());
        ArrayList<SalesSlip> dsPhieuBanHang=SalesSlipDAO.getListSalesSlipByDateValue(ngayBatDau, ngayKetThuc);
        LoadTableDanhSachPhieuBan_TabQLBanHang(dsPhieuBanHang);
        
    }//GEN-LAST:event_btnTimKiemTheoNgay_TabQLBanHangActionPerformed

    private void tbDanhSachPhieuBan_TabQLBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachPhieuBan_TabQLBanHangMouseClicked
        // TODO add your handling code here:
        int row=tbDanhSachPhieuBan_TabQLBanHang.getSelectedRow();
        String maPhieuBan=tbDanhSachPhieuBan_TabQLBanHang.getValueAt(row, 0).toString();
        LoadtableChiTietPhieuBan_TabQLBanHang(maPhieuBan);
    }//GEN-LAST:event_tbDanhSachPhieuBan_TabQLBanHangMouseClicked

    private void btnTimKiemTheoMaNV_TabQLBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemTheoMaNV_TabQLBanHangActionPerformed
        // TODO add your handling code here:
        SalePerson s=(SalePerson)cbbTimTheoNhanVien_TabQLBanHang.getSelectedItem();
        ArrayList<SalesSlip> dsPhieuBanHang=SalesSlipDAO.getListSalesSlipByIDSalePerson(s.getMaNhanVien());
        LoadTableDanhSachPhieuBan_TabQLBanHang(dsPhieuBanHang);
    }//GEN-LAST:event_btnTimKiemTheoMaNV_TabQLBanHangActionPerformed

    private void btnXuatTatCa_TabQLBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatTatCa_TabQLBanHangActionPerformed
        // TODO add your handling code here:
         ArrayList<SalesSlip> dsPhieuBanHang=SalesSlipDAO.getAll();
        LoadTableDanhSachPhieuBan_TabQLBanHang(dsPhieuBanHang);
    }//GEN-LAST:event_btnXuatTatCa_TabQLBanHangActionPerformed

    private void cbbTheLoai_TabQLSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbTheLoai_TabQLSanPhamMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2)
        {
            LoadComboboxTheLoai_TabQLSanPham();
        }
//        else
//        {
//            Category category=(Category)cbbTheLoai_TabQLSanPham.getSelectedItem();
//            if(category.getMaTheLoai().equals("addNew"))
//            {
//                InsertCategoryGui insertCategoryGui=new InsertCategoryGui();
//                insertCategoryGui.show();
//            }
//        }
    }//GEN-LAST:event_cbbTheLoai_TabQLSanPhamMouseClicked

    private void cbbNhaXuatBan_TabQLSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbNhaXuatBan_TabQLSanPhamMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2)
        {
            LoadComboboxNhaXuatBan_TabQLSanPham();
        }
//        else
//        {
//            Publisher nxb=(Publisher)cbbNhaXuatBan_TabQLSanPham.getSelectedItem();
//            if(nxb.getMaNhaXuatBan().equals("addNew"))
//            {
//                InsertPublisherGUI pubisherGUI=new InsertPublisherGUI();
//                pubisherGUI.show();
//            }
//        }
    }//GEN-LAST:event_cbbNhaXuatBan_TabQLSanPhamMouseClicked

    private void btnXoa_TabQLSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_TabQLSanPhamActionPerformed
        // TODO add your handling code here:
        String maSanPham=edtMaSach_TabQLSanPham.getText();
        if(ProductDAO.NgungKinhDoanhMotSanPham(maSanPham)>0)
        {
            JOptionPane.showMessageDialog(this, "Đã khóa");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }//GEN-LAST:event_btnXoa_TabQLSanPhamActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ViewLogin v=new ViewLogin();
        v.show();
        this.hide();
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btnLoadChart_TabQLDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadChart_TabQLDoanhThuActionPerformed
        // TODO add your handling code here:
        LoadJtreeChart();
    }//GEN-LAST:event_btnLoadChart_TabQLDoanhThuActionPerformed

    /**
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminPageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPageGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnChonAnh_TabQLNhanVien;
    private javax.swing.JButton btnChonAnh_TabQLSanPham;
    private javax.swing.JButton btnLoadChart_TabQLDoanhThu;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSuaChiTietNhapHang_TabQLNhapHang;
    private javax.swing.JButton btnSuaNV_TabQLNhanVien;
    private javax.swing.JButton btnSua_TabQLSanPham;
    private javax.swing.JButton btnTaoMoiPNH_TabQLNhapHang;
    private javax.swing.JButton btnThemChiTietNhapHang_TabQLNhapHang;
    private javax.swing.JButton btnThemNhaVien_TabQLNhanVien;
    private javax.swing.JButton btnThem_TabQLSanPham;
    private javax.swing.JButton btnThongKeDoanhThu;
    private javax.swing.JButton btnTimKiemTheoMaNV_TabQLBanHang;
    private javax.swing.JButton btnTimKiemTheoNgay_TabQLBanHang;
    private javax.swing.JButton btnTimKiem_TabQLNhanVien;
    private javax.swing.JButton btnTimKiem_TabQLNhapHang;
    private javax.swing.JButton btnXoaChiTietNhapHang_TabQLNhapHang;
    private javax.swing.JButton btnXoaNV_tabQLNhanVien;
    private javax.swing.JButton btnXoaPNH_TabQLNhapHang;
    private javax.swing.JButton btnXoa_TabQLSanPham;
    private javax.swing.JButton btnXuatTatCa_TabQLBanHang;
    private javax.swing.JButton btnXuatThongKeDoanhSoBanHang_TabQLBanHang;
    private javax.swing.JComboBox<Object> cbbChucVuNV_TabQLNhanVien;
    private javax.swing.JComboBox<String> cbbGioiTinh_TabQLNhanVien;
    private javax.swing.JComboBox<Object> cbbKetQuaTimKiem_TabQLNhapHang;
    private javax.swing.JComboBox<Object> cbbKetQua_TabQLNhanVien;
    private javax.swing.JComboBox<String> cbbLoaiTaiKhoan_TabQLNhanVien;
    private javax.swing.JComboBox<Object> cbbNhaCungCap_TabQLNhapHang;
    private javax.swing.JComboBox<Object> cbbNhaXuatBan_TabQLSanPham;
    private javax.swing.JComboBox<Object> cbbSach_TabQLNhapHang;
    private javax.swing.JComboBox<Object> cbbTheLoaiSach_TabQLNhapHang;
    private javax.swing.JComboBox<Object> cbbTheLoai_TabQLSanPham;
    private javax.swing.JComboBox<String> cbbTimKiemTheoLoai_TabQLNhanVien;
    private javax.swing.JComboBox<Object> cbbTimTheoNhanVien_TabQLBanHang;
    private javax.swing.JComboBox<String> cbbTimTheo_TabQLNhapHang;
    private javax.swing.JComboBox<String> cbbTrangThai_TabQLNhanVien;
    private javax.swing.JTextPane edtCCCD_TabQLNhanVien;
    private javax.swing.JTextPane edtGiaBan_TabQLSanPham;
    private javax.swing.JTextPane edtMaNV_TabQLNhanVien;
    private javax.swing.JTextPane edtMaNhanVien_TabQLNhapHang;
    private javax.swing.JTextPane edtMaPhieuNhap_TabQLNhapHang;
    private javax.swing.JTextPane edtMaSach_TabQLNhapHang;
    private javax.swing.JTextPane edtMaSach_TabQLSanPham;
    private javax.swing.JPasswordField edtMatKhauNV_TabQLNhanVien;
    private com.toedter.calendar.JDateChooser edtNgayBatDau_TablQLBanHang;
    private com.toedter.calendar.JDateChooser edtNgayKetThuc_TablQLBanHang;
    private com.toedter.calendar.JDateChooser edtNgayNhap_TabQLNhapHang;
    private com.toedter.calendar.JDateChooser edtNgaySinh_TabQLNhanVien;
    private com.toedter.calendar.JDateChooser edtNgayTimKiem_TabQLPhieuNhap;
    private com.toedter.calendar.JDateChooser edtNgayVaoLam_TabQLNhanVien;
    private javax.swing.JTextPane edtQueQuan_TabNhaVien;
    private javax.swing.JTextPane edtSerial_TabQLSanPham;
    private javax.swing.JTextPane edtSoDienThoaiNV_TabQLNhanVien;
    private javax.swing.JTextPane edtSoLuongDanBan_TabQLSanPham;
    private javax.swing.JTextPane edtSoLuongTon_TabQLSanPham;
    private javax.swing.JTextPane edtSoLuong_TabQLNhapHang;
    private javax.swing.JTextPane edtTacGia_TabQLSanPham;
    private javax.swing.JTextPane edtTenDangNhapNV_TabQlNhanVien;
    private javax.swing.JTextPane edtTenNV_TabQLNhanVien;
    private javax.swing.JTextPane edtTenSach_TabQLSanPham;
    private javax.swing.JTextPane edtThongTinTimKiem_TabQLNhapHang;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel jlabelHinhAnh_TabQLSanPham;
    private javax.swing.JLabel labelHinhAnhNV_TabQLNhanVien;
    private javax.swing.JLabel lblDoanhThuThang_TabQLDanhThu;
    private javax.swing.JLabel lblSachDangDaBanTrongThang_TabQLDanhThu;
    private javax.swing.JLabel lblSachDangDaBan_TabQLDanhThu;
    private javax.swing.JLabel lblSachDangKD_TabQLDanhThu;
    private javax.swing.JPanel tabBanHang;
    private javax.swing.JPanel tabNhapHang;
    private javax.swing.JPanel tabQLDoanhThu;
    private javax.swing.JPanel tabQLHoaDon;
    private javax.swing.JPanel tabQLNhanVien;
    private javax.swing.JPanel tabQLSanPham;
    private javax.swing.JTabbedPane tableTab;
    private javax.swing.JTable tbChiTietNhapHang_TabQLNhapHang;
    private javax.swing.JTable tbChiTietPhieuBan_TabQLBanHang;
    private javax.swing.JTable tbDanhSachPhieuBan_TabQLBanHang;
    private javax.swing.JTable tb_SanPham_TabQLSanPham;
    private javax.swing.JTextPane txt_data;
    // End of variables declaration//GEN-END:variables
}
