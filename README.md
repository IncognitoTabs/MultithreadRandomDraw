# MultithreadRandomDraw

# Testthem code design
<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="testthem.aspx.cs" Inherits="affa.testthem" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
  
<head runat="server">
  
    <title></title>
  
    <style type="text/css">
        .auto-style1 {
            width: 535px;
            float: left;
            border-style: double;
            border-width: 3px;
        }
      
        .auto-style8 {
            height: 64px;
            text-align: center;
            border-style: groove;
            border-width: 1px;
        }
      
        .auto-style16 {
            width: 248px;
            border-style: groove;
            border-width: 1px;
        }
      
        .auto-style19 {
            width: 548px;
            border-style: groove;
            border-width: 1px;
        }
        .auto-style20 {
            width: 548px;
            height: 27px;
            border-style: groove;
            border-width: 1px;
        }
      
        .auto-style21 {
            width: 248px;
            height: 27px;
            border-style: groove;
            border-width: 1px;
        }
        .auto-style22 {
            border-style: groove;
            border-width: 1px;
        }
    </style>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <table align="left" class="auto-style1">
                <tr>
                    <td class="auto-style8" colspan="2"><strong>THÔNG TIN SINH VIÊN</strong></td>
                </tr>
                <tr>
                    <td class="auto-style21">Nhập mã sinh viên</td>
                    <td class="auto-style20">
                        <asp:TextBox ID="edtMaSV" runat="server"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <td class="auto-style21">Nhập tên sinh viên</td>
                    <td class="auto-style20">
                        <asp:TextBox ID="edtTenSV" runat="server"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <td class="auto-style16">Nhập số điện thoại</td>
                    <td class="auto-style19">
                        <asp:TextBox ID="edtSoDT" runat="server"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <td class="auto-style16">Nhập Email</td>
                    <td class="auto-style19">
                        <asp:TextBox ID="edtEmail" runat="server"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <td class="auto-style22" colspan="2">
                        <asp:Button ID="btnThem" runat="server" OnClick="btnThem_Click" Text="Thêm" />
                    </td>
                </tr>
            </table>
        </div>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
      
        <script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.19.3/jquery.validate.min.js"></script>
      
        <script type="text/javascript">
            $(document).ready(function () {
          
                $("#form1").validate({
          
                    rules: {
                        "<%=edtMaSV.UniqueID %>": {required: true },
          
                        "<%=edtTenSV.UniqueID %>": {required: true},
          
                        "<%=edtSoDT.UniqueID %>": { required: true},
          
                        "<%=edtEmail.UniqueID %>": { required: true},
                    },
                    messages: {
          
                        "<%=edtMaSV.UniqueID %>": { required: " Không được để trống." },
          
                        "<%=edtTenSV.UniqueID %>": {required: " Không được để trống."},
          
                        "<%=edtSoDT.UniqueID %>": {required: " Không được để trống."},
          
                        "<%=edtEmail.UniqueID %>": {required: " Không được để trống."},
                    },
                });
            });
        </script>
    </form>
    </body>
</html>

# Testthem code behind
          
using System;
          
using System.Collections.Generic;
          
using System.Data;
          
using System.Data.SqlClient;
          
using System.Web.Services;
          
using System.Web.UI;
          

namespace affa
{
    
          public partial class testthem : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        protected void btnThem_Click(object sender, EventArgs e)
        {
            Insert(edtMaSV.Text.ToString(), edtTenSV.Text.ToString(), edtSoDT.Text.ToString(), edtEmail.Text.ToString());
        }

        [WebMethod]
        public static void Insert(string masv, string tensv, string sdt, string email)
        {
            using (SqlConnection con = new SqlConnection("Data Source=LAPTOP-5LK6RF58\\SONGOLUU;Initial Catalog=Sinhvien1;Persist Security Info=True;User ID=sa;Password=luu12345"))
            {
                using (SqlCommand cmd = new SqlCommand("INSERT INTO SINHVIEN (MASINHVIEN, TENSINHVIEN, SODT, EMAIL) VALUES (@MASINHVIEN, @TENSINHVIEN, @SODT, @EMAIL)", con))
                {
                    cmd.Parameters.AddWithValue("MASINHVIEN", masv);
                    cmd.Parameters.AddWithValue("TENSINHVIEN", tensv);
                    cmd.Parameters.AddWithValue("SODT", sdt);
                    cmd.Parameters.AddWithValue("EMAIL", email);
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
        }
    }
}

# themSinhVien code design
<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="themSinhVien.aspx.cs" Inherits="affa.themSinhVien" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Thêm sinh viên</title>
</head>
<body>

    <form id="form1" runat="server">
        <asp:Table ID="Table1" runat="server" Width="500px" BorderStyle="Double" BorderWidth="5px">
            <asp:TableRow>
                <asp:TableHeaderCell ColumnSpan="2" BorderStyle="Groove" BorderWidth="2px"><h3>THÔNG TIN SINH VIÊN</h3></asp:TableHeaderCell>
            </asp:TableRow>
            <asp:TableRow>
                <asp:TableHeaderCell BorderStyle="Groove" BorderWidth="2px" HorizontalAlign="Left">Nhập mã sinh viên</asp:TableHeaderCell>
                <asp:TableHeaderCell BorderStyle="Groove" BorderWidth="2px" HorizontalAlign="Left">
                    <asp:TextBox ID="edtMaSV" runat="server"></asp:TextBox>
                </asp:TableHeaderCell>
            </asp:TableRow>
            <asp:TableRow>
                <asp:TableHeaderCell BorderStyle="Groove" BorderWidth="2px" HorizontalAlign="Left">Nhập tên sinh viên</asp:TableHeaderCell>
                <asp:TableHeaderCell BorderStyle="Groove" BorderWidth="2px" HorizontalAlign="Left">
                    <asp:TextBox ID="edtTenSV" runat="server"></asp:TextBox>
                </asp:TableHeaderCell>
            </asp:TableRow>
            <asp:TableRow>
                <asp:TableHeaderCell BorderStyle="Groove" BorderWidth="2px" HorizontalAlign="Left">Nhập số điện thoại</asp:TableHeaderCell>
                <asp:TableHeaderCell BorderStyle="Groove" BorderWidth="2px" HorizontalAlign="Left">
                    <asp:TextBox ID="edtSoDT" runat="server"></asp:TextBox>
                </asp:TableHeaderCell>
            </asp:TableRow>
            <asp:TableRow>
                <asp:TableHeaderCell BorderStyle="Groove" BorderWidth="2px" HorizontalAlign="Left">Nhập email</asp:TableHeaderCell>
                <asp:TableHeaderCell BorderStyle="Groove" BorderWidth="2px" HorizontalAlign="Left">
                    <asp:TextBox ID="edtEmail" runat="server"></asp:TextBox>
                </asp:TableHeaderCell>
            </asp:TableRow>
        </asp:Table>
        <asp:Button ID="btnThem" runat="server" Text="Thêm" OnClick="btnThem_Click" />
    </form>

        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.19.3/jquery.validate.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#form1").validate({
                    rules: {
                        "<%=edtMaSV.UniqueID %>": {
                            required: true
                        },
                        "<%=edtTenSV.UniqueID %>": {
                            required: true
                        },
                        "<%=edtSoDT.UniqueID %>": {
                            required: true
                        },
                        "<%=edtEmail.UniqueID %>": {
                            required: true
                        },
                    },
                    messages: {
                        "<%=edtMaSV.UniqueID %>": {
                            required: " Không được để trống."
                        },
                        "<%=edtTenSV.UniqueID %>": {
                            required: " Không được để trống."
                        },
                        "<%=edtSoDT.UniqueID %>": {
                            required: " Không được để trống."
                        },
                        "<%=edtEmail.UniqueID %>": {
                            required: " Không được để trống."
                        },
                    },
                });
            });
        </script>
</body>
</html>


# themSinhVien code behind như Testthem code behind
# suaSinhVien code design
<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="suaSinhVien.aspx.cs" Inherits="affa.suaSinhVien" %>
  

<!DOCTYPE html>
  

<html xmlns="http://www.w3.org/1999/xhtml">
  
<head runat="server">
  
    <title>Sửa sinh viên</title>
  
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  
    <script type="text/javascript">
      
        $(function () {
            $("#edtMaSV").keypress(function (event) {
                if (event.keyCode == 13) {
                    $.ajax({
                        type: "POST",
                        contentType: "application/json; charset=utf-8",
                        url: "suaSinhVien.aspx/getOneSinhVien",
                        data: "{masv : '" + $(this).val() + "'}",
                        dataType: "json",
                        success: function (data) {
                            if (data.d == null) alert("Không tồn tại mã sinh viên này");
                            else {
                                $.each(data.d, function (key, value) {
                                    $("#edtTenSV").val(value.TENSINHVIEN);
                                    $("#edtSoDT").val(value.SODT);
                                    $("#edtEmail").val(value.EMAIL);
                                });
                            }

                        },
                        error: function (result) {
                            alert("Lỗi rồi bà");
                        }
                    });
                }
            });
            $("#btnSua").click(function () {
                if ($("#edtTenSV").val() == "" || $("#edtSoDT").val() == "" || $("#edtEmail").val() == "") alert("Vui lòng nhập đầy đủ");
                else
                $.ajax({
                    type: "POST",
                    contentType: "application/json; charset=utf-8",
                    url: "suaSinhVien.aspx/Update",
                    data: "{masv : '" + $("#edtMaSV").val() + "', tensv: '" + $("#edtTenSV").val() + "', sdt: '" + $("#edtSoDT").val() + "', email: '" + $("#edtEmail").val() + "' }",
                    dataType: "json",
                    success: function () {
                        alert("Sửa thành công");

                    },
                    error: function (result) {
                        alert("Lỗi rồi bà");
                    }
                });
            });
        });
    </script>
</head>
<body>
    <h3>SỬA THÔNG TIN SINH VIÊN</h3>
    <table border="1" >
        <tr>
            <td>Mã sinh viên</td>
            <td><input type="text" id="edtMaSV" /></td>
        </tr>
         <tr>
            <td>Tên sinh viên</td>
            <td><input type="text" id="edtTenSV" /></td>
        </tr>
        <tr>
            <td>Số điện thoại</td>
            <td><input type="text" id="edtSoDT" /></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" id="edtEmail" /></td>
        </tr>
        <tr>
             <td><input type="button" id="btnSua" value="Sửa" style="cursor: pointer;"/></td>
        </tr>
    </table>    
</body>
</html>

# suaSinhvien code behind
using System;
  
using System.Collections.Generic;
  
using System.Data;
  
using System.Data.SqlClient;
  
using System.Web.Services;
  
using System.Web.UI;

namespace affa
{
    
  public partial class suaSinhVien : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        [WebMethod]
        public static SINHVIEN[] getOneSinhVien(string masv)
        {
            DataTable dt = new DataTable();
            List<SINHVIEN> details = new List<SINHVIEN>();

            using (SqlConnection con = new SqlConnection("Data Source=LAPTOP-5LK6RF58\\SONGOLUU;Initial Catalog=Sinhvien1;Persist Security Info=True;User ID=sa;Password=luu12345"))
            {
                using (SqlCommand cmd = new SqlCommand("SELECT * FROM SINHVIEN WHERE MASINHVIEN = @masv", con))
                {
                    cmd.Parameters.AddWithValue("masv", masv);
                    con.Open();
                    SqlDataAdapter da = new SqlDataAdapter(cmd);
                    da.Fill(dt);
                    foreach (DataRow dtrow in dt.Rows)
                    {
                        SINHVIEN sinhvien = new SINHVIEN();
                        sinhvien.MASINHVIEN = masv;
                        sinhvien.TENSINHVIEN = dtrow["TENSINHVIEN"].ToString();
                        sinhvien.SODT = dtrow["SODT"].ToString();
                        sinhvien.EMAIL = dtrow["EMAIL"].ToString();
                        details.Add(sinhvien);
                    }
                }
            }
            if(details.Count > 0) return details.ToArray();
            return null;
        }
        [WebMethod]
        public static void Update(string masv, string tensv, string sdt, string email)
        {
            
            using (SqlConnection con = new SqlConnection("Data Source=LAPTOP-5LK6RF58\\SONGOLUU;Initial Catalog=Sinhvien1;Persist Security Info=True;User ID=sa;Password=luu12345"))
            {
                using (SqlCommand cmd = new SqlCommand("UPDATE SINHVIEN SET TENSINHVIEN = @tensv, SODT = @sdt, EMAIL = @email WHERE MASINHVIEN = @masv" , con))
                {
                    cmd.Parameters.AddWithValue("masv", masv);
                    cmd.Parameters.AddWithValue("tensv", tensv);
                    cmd.Parameters.AddWithValue("sdt", sdt);
                    cmd.Parameters.AddWithValue("email", email);
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
        }
        public static SINHVIEN[] BindDatatoDropdown1(string masv)
        {
            DataTable dt = new DataTable();
            List<SINHVIEN> details = new List<SINHVIEN>();

            using (SqlConnection con = new SqlConnection("Data Source=LAPTOP-5LK6RF58\\SONGOLUU;Initial Catalog=Sinhvien1;Persist Security Info=True;User ID=sa;Password=luu12345"))
            {
                using (SqlCommand cmd = new SqlCommand("SELECT TENSINHVIEN FROM SINHVIEN WHERE MASINHVIEN = @masv", con))
                {
                    cmd.Parameters.AddWithValue("masv", masv);
                    con.Open();
                    SqlDataAdapter da = new SqlDataAdapter(cmd);
                    da.Fill(dt);
                    foreach (DataRow dtrow in dt.Rows)
                    {
                        SINHVIEN sinhvien = new SINHVIEN();
                        sinhvien.MASINHVIEN = masv;
                        sinhvien.TENSINHVIEN = dtrow["TENSINHVIEN"].ToString();
                        sinhvien.SODT = dtrow["SODT"].ToString();
                        sinhvien.EMAIL = dtrow["EMAIL"].ToString();
                        details.Add(sinhvien);
                    }
                }
            }
            return details.ToArray();
        }
    }
}

# xoaSinhVien code design
<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="xoaSinhVien.aspx.cs" Inherits="affa.xoaSinhVien" %>

<!DOCTYPE html>
  

<html xmlns="http://www.w3.org/1999/xhtml">
  
<head runat="server">
  
    <title>Xóa sinh viên</title>
  
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  
    <script type="text/javascript">
      
        $(document).ready(function () {
            getData();
        });
        function getData() {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "xoaSinhVien.aspx/GetAllSV",
                data: "{}",
                dataType: "json",
                success: function (data) {
                    if (data.d == null) alert("Không tồn tại sinh viên nào");
                    else {
                        $("#tbl").find("tr:gt(0)").remove();
                        $.each(data.d, function (key, value) {
                            $("#tbl").append(
                                '<tr><td>' + value.MASINHVIEN + '</td>' +
                                '<td>' + value.TENSINHVIEN + '</td>' +
                                '<td>' + value.SODT + '</td>' +
                                '<td>' + value.EMAIL + '</td>' +
                                `<td><a style="text-decoration: underline; cursor: pointer;" onclick="deleteData('${value.MASINHVIEN}')">Xóa</a></td></tr>`
                            );

                        });
                    }

                },
                error: function (result) {
                    alert("Lỗi rồi bà");
                }
            });
        }
        function deleteData(masv) {
            $.ajax({
                type: "POST",
                contentType: "application/json; charset=utf-8",
                url: "xoaSinhVien.aspx/Delete",
                data: "{masv: '" + masv + "'}",
                dataType: "json",
                success: function () {
                    getData();
                    alert("Xóa thành công");
                },
                error: function (result) {
                    alert("Lỗi rồi bà");
                }
            });
        }
    </script>
</head>
<body>
    <h3>DANH SÁCH SINH VIÊN</h3>
    <table border="1" id="tbl">
        <tr>
            <td>Mã sinh viên</td>
            <td>Tên sinh viên</td>
            <td>Số điện thoại</td>
            <td>Email</td>
            <td>Xóa</td>
        </tr>
    </table>
</body>
</html>

# xoaSinhVien code behind
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.Services;
using System.Web.UI;

namespace affa
{
    
  public partial class xoaSinhVien : System.Web.UI.Page
    {
       
  protected void Page_Load(object sender, EventArgs e)
        {

        }
        [WebMethod]
        public static SINHVIEN[] GetAllSV()
        {
            List<SINHVIEN> details = new List<SINHVIEN>();

            using (SqlConnection con = new SqlConnection("Data Source=LAPTOP-5LK6RF58\\SONGOLUU;Initial Catalog=Sinhvien1;Persist Security Info=True;User ID=sa;Password=luu12345"))
            {
                using (SqlCommand cmd = new SqlCommand("SELECT * FROM SINHVIEN", con))
                {
                    con.Open();
                    SqlDataAdapter da = new SqlDataAdapter(cmd);
                    DataTable dt = new DataTable();
                    da.Fill(dt);
                    foreach (DataRow dtrow in dt.Rows)
                    {
                        SINHVIEN sinhvien = new SINHVIEN();
                        sinhvien.MASINHVIEN = dtrow["MASINHVIEN"].ToString();
                        sinhvien.TENSINHVIEN = dtrow["TENSINHVIEN"].ToString();
                        sinhvien.SODT = dtrow["SODT"].ToString();
                        sinhvien.EMAIL = dtrow["EMAIL"].ToString();
                        details.Add(sinhvien);
                    }
                }
            }
            if (details.Count > 0) return details.ToArray();
            return null;
        }
        [WebMethod]
        public static void Delete(string masv)
        {

            using (SqlConnection con = new SqlConnection("Data Source=LAPTOP-5LK6RF58\\SONGOLUU;Initial Catalog=Sinhvien1;Persist Security Info=True;User ID=sa;Password=luu12345"))
            {
                using (SqlCommand cmd = new SqlCommand("DELETE SINHVIEN WHERE MASINHVIEN = @masv", con))
                {
                    cmd.Parameters.AddWithValue("masv", masv);
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }
            }
        }
    }
}
CLASS SINHVIEN
using System;
  
using System.Collections.Generic;
  
using System.Linq;
  
using System.Web;
  

namespace affa
{
    public class SINHVIEN
    {
        public string MASINHVIEN { get; set; }
  
        public string TENSINHVIEN { get; set; }
  
        public string SODT { get; set; }
  
        public string EMAIL { get; set; }

    }
}

# BÀI CÚ LỪA
## Them Design
<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Them.aspx.cs" Inherits="cú_lừa_1.Them" %>

<!DOCTYPE html>
  

  
<html xmlns="http://www.w3.org/1999/xhtml">
  
<head runat="server">
  
    <title></title>
  
    <style type="text/css">
      
        .auto-style1 {
            width: 100%;
            border-style: solid;
            border-width: 1px;
        }
      
        .auto-style2 {
            text-align: center;
            height: 70px;
            border-style: groove;
            border-width: 1px;
        }
      
        .auto-style3 {
            width: 135px;
            border-style: groove;
            border-width: 1px;
        }
      
        .auto-style4 {
            border-style: groove;
            border-width: 1px;
        }
      
    </style>
</head>
<body style="width: 541px">
    <form id="form1" runat="server">
        <div>
            <table class="auto-style1">
                <tr>
                    <td class="auto-style2" colspan="2"><strong>THÔNG TIN SẢN PHẨM</strong></td>
                </tr>
                <tr>
                    <td class="auto-style3">Mã sản phẩm</td>
                    <td class="auto-style4">
                        <asp:TextBox ID="edtMaSP" runat="server"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <td class="auto-style3">Tên sản phẩm</td>
                    <td class="auto-style4">
                        <asp:TextBox ID="edtTenSP" runat="server"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <td class="auto-style3">Giá bán</td>
                    <td class="auto-style4">
                        <asp:TextBox ID="edtGiaBan" runat="server"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <td class="auto-style3">Ảnh</td>
                    <td class="auto-style4">
                        <asp:FileUpload ID="UploadAnh" runat="server" />
                    </td>
                </tr>
                <tr>
                    <td class="auto-style4" colspan="2">
                        <asp:Button ID="btnThem" runat="server" OnClick="btnThem_Click" Text="Thêm" />
                    </td>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>

# Them code behind
using System;
  
using System.Collections.Generic;
  
using System.Linq;
  
using System.Web;
  
using System.Web.Services;
  
using System.Web.UI;
  
using System.Web.UI.WebControls;
  
using System.Data;
  
using System.Data.SqlClient;
  

namespace cú_lừa_1
{
  
    
  public partial class Them : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        [WebMethod]
        public void Insert(string masp,  string tensp, int giaban, string anh)
        {
            using(SqlConnection con = new SqlConnection("Data Source=LAPTOP-5LK6RF58\\SONGOLUU;Initial Catalog=Culua1;Integrated Security=True"))
            {
                using(SqlCommand cmd = new SqlCommand("INSERT INTO SANPHAM VALUES (@MASP, @TENSP, @GIABAN, @ANH)", con))
                {
                    cmd.Parameters.AddWithValue("MASP", masp);
                    cmd.Parameters.AddWithValue("TENSP", tensp);
                    cmd.Parameters.AddWithValue("GIABAN", giaban);
                    cmd.Parameters.AddWithValue("ANH", anh);
                    con.Open();
                    cmd.ExecuteNonQuery();
                    con.Close();
                }    
            }    
        }
        protected void btnThem_Click(object sender, EventArgs e)
        {
            FileUpload file = (FileUpload) Form.FindControl("UploadAnh");
            String path = Server.MapPath("~/Image/");
            file.PostedFile.SaveAs(path + file.FileName);
            string masp = edtMaSP.Text.ToString();
            string tensp = edtTenSP.Text.ToString();
            int giaban = Convert.ToInt32(edtGiaBan.Text.ToString());
            string anh = file.FileName;
            Insert(masp, tensp, giaban, anh);
        }
    }
}


# Ảnh trên là phần Hienthi design, comment hết phần hiển thị sản phẩm trong template đi, để lại dòng phía trên cùng thôi, trong đó thêm 1 cái placeholder như ảnh vào
# Hienthi code behind
using System;
  
using System.Collections.Generic;
  
using System.Data.SqlClient;
  
using System.Linq;
  
using System.Text;
  
using System.Web;
  
using System.Web.UI;
  
using System.Web.UI.WebControls;
  
using System.Data;
  

namespace cú_lừa_1
{
    
  public partial class HienThi : System.Web.UI.Page
  
  {
        StringBuilder sanpham = new StringBuilder();
  
        
  protected void Page_Load(object sender, EventArgs e)
        {
            using (SqlConnection con = new SqlConnection("Data Source=LAPTOP-5LK6RF58\\SONGOLUU;Initial Catalog=Culua1;Integrated Security=True"))
            {
                con.Open();
                using (SqlCommand cmd = new SqlCommand("SELECT * FROM SANPHAM", con))
                {
                    SqlDataReader dr = cmd.ExecuteReader();

                    if (dr.HasRows)
                    {
                        while (dr.Read())
                        {
                            sanpham.Append("<div class=\"col-md-3 col-lg-3 mb-4 text-center\">");
                            sanpham.Append("<div class=\"product-entry border\">");
                            sanpham.Append("<a href=\"#\" class=\"prod-img\">");
                            sanpham.Append("<img src=\"Image/" + dr[3] + "\" class=\"img-fluid\" alt=\"Free html5 bootstrap 4 template\">");
                            sanpham.Append("</a>");
                            sanpham.Append("<div class=\"desc\">");
                            sanpham.Append("<h2><a href=\"#\">" + dr[1] + "</a></h2>");
                            sanpham.Append("<span class=\"price\">$" + dr[2] + "</span>");
                            sanpham.Append("</div>");
                            sanpham.Append("</div>");
                            sanpham.Append("</div>");
                        }
                    }
                    PlaceHolder1.Controls.Add(new Literal { Text = sanpham.ToString() });
                    dr.Close();
                    con.Close();
                }
            }
        }
    }
}
