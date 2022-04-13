package easymall.dao;

import java.util.List;
import java.util.Map;

import easymall.po.Products;

public interface ProductsDao {

	public List<String> allcategories();

	public List<Products> prodlist(Map<String, Object> map);

	public Products oneProduct(String product_id);

	public List<Products> prodclass(String category);

	public Object findByImgurl(String imgurl);

	public void save(Products products);

	public List<Products> allprods();

	public void updateSaleStatus(Map<String, Object> map);

	public void updateSoldNum(Map<String, Object> map);
}
