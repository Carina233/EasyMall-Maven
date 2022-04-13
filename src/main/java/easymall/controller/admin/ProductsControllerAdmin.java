package easymall.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Products;
import easymall.pojo.MyProducts;
import easymall.service.ProductsService;

@Controller
@RequestMapping("/admin")
public class ProductsControllerAdmin {

	@Autowired
	private ProductsService productsService;

	@RequestMapping("/prodlist")
	public String prodlist(Model model) {

		double _minPrice = 0;
		double _maxPrice = Double.MAX_VALUE;

		Map<String, Object> map = new HashMap<>();
		map.put("name", "");
		map.put("category", "");
		map.put("minPrice", _minPrice);
		map.put("maxPrice", _maxPrice);

		List<Products> products = productsService.allprods();
		System.out.println(products.size());

		model.addAttribute("products", products);

		return "/admin/prod_list";

	}

	@RequestMapping("/addprod")
	public String addprod(Model model) {

		List<String> categories = productsService.allcategories();
		model.addAttribute("categories", categories);
		model.addAttribute("myproducts", new MyProducts());
		return "/admin/add_prod";
	}

	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute MyProducts myproducts, HttpServletRequest request, Model model)
			throws Exception {
		String msg = productsService.save(myproducts, request);
		model.addAttribute("msg", msg);

		return "redirect:/admin/addprod";
	}

	@RequestMapping("/OnSale")
	public String OnSale(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", 1);
		productsService.updateSaleStatus(map);
		System.out.println("shangjia ");
		return "redirect:/admin/prodlist";
	}

	@RequestMapping("/OffSale")
	public String OffSale(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", 0);
		productsService.updateSaleStatus(map);
		System.out.println("xiajia ");
		return "redirect:/admin/prodlist";
	}
}
