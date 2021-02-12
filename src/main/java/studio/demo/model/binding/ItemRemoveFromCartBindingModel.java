package studio.demo.model.binding;

public class ItemRemoveFromCartBindingModel extends  BaseBindingModel {
    private String itemId;
    private String userId;


    public ItemRemoveFromCartBindingModel() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
