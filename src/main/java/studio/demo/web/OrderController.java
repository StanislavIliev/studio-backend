package studio.demo.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import studio.demo.exception.*;
import studio.demo.model.binding.OrderAddBindingModel;
import studio.demo.model.binding.ProcedureBindingModel;
import studio.demo.model.entity.Order;
import studio.demo.model.entity.Procedure;
import studio.demo.model.service.OrderServiceModel;
import studio.demo.model.view.OrderViewModel;
import studio.demo.service.OrderService;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {
    ConcurrentMap<String , Order> orders = new ConcurrentHashMap<>();

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrderController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find orders by id",
            notes = "Provide id to look up for a specific order",
            response = Order.class
    )
    public  Order getOrder (@ApiParam(value = "Id value for the order you need to retrieve"
            ,required = true)@PathVariable String id){
        return orders.get(id);
    }



    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderViewModel> addOrder
            (@RequestBody OrderAddBindingModel order) throws OrderNullException,
            OrderWithThisNameExist, ProcedureNullException, UserNullException, ProductNullException, OrderEmptyException {
    	 OrderServiceModel ooo = this.orderService.addOrder(this.modelMapper.map
                (order, OrderServiceModel.class));

        OrderViewModel orderView = this.modelMapper.map(ooo, OrderViewModel.class);

        return new ResponseEntity<>(orderView, HttpStatus.OK);
    }


    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderViewModel> update
            (@RequestBody OrderAddBindingModel order) throws OrderWithThisNameDoesNotExist {

        OrderServiceModel updatedOrder =
                this.orderService.update(order);

        return new ResponseEntity<OrderViewModel>(this.modelMapper.map(updatedOrder
                , OrderViewModel.class),  HttpStatus.OK);

    }



    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderViewModel>> allOrders() {
        List<OrderViewModel> orders = this.orderService.findAllItems()
                .stream()
                .map(ccc -> this.modelMapper.map(ccc, OrderViewModel.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }


    @GetMapping("/details")
    public ModelAndView details(@RequestParam("id") String id, ModelAndView modelAndView) {
        modelAndView.addObject("order", this.orderService.findById(id));
        modelAndView.setViewName("details-order");
        return modelAndView;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteOrder(@Valid @RequestBody OrderAddBindingModel order) throws OrderWithThisIdNotExist {
        Order ooo = this.modelMapper.map(order,Order.class);
        boolean isOrderDeleted = this.orderService.delete(ooo.getId());
        return new ResponseEntity<>(isOrderDeleted, HttpStatus.OK);
    }
}

