package easymall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import easymall.po.Products;
import easymall.pojo.MyProducts;

public interface ProductsService {

	public List<String> allcategories();

	public List<Products> prodlist(Map<String, Object> map);

	public Products oneProduct(String product_id);

	public List<Products> prodclass(String prodclass);

	public String save(MyProducts myproducts, HttpServletRequest request);

	public List<Products> allprods();

	public void updateSaleStatus(Map<String, Object> map);

}
