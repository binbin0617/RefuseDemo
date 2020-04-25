package com.bin.refusedemo.bean;

import java.util.List;

public class BianBean {
    /**
     * reason : success
     * result : [{"id":"27","itemName":"拔丝苹果","itemCategory":"湿垃圾"},{"id":"50","itemName":"烂苹果","itemCategory":"湿垃圾"},{"id":"62","itemName":"苹果籽","itemCategory":"湿垃圾"},{"id":"63","itemName":"苹果果核","itemCategory":"湿垃圾"},{"id":"64","itemName":"苹果核","itemCategory":"湿垃圾"},{"id":"65","itemName":"苹果泥","itemCategory":"湿垃圾"},{"id":"66","itemName":"苹果派","itemCategory":"湿垃圾"},{"id":"67","itemName":"苹果皮","itemCategory":"湿垃圾"},{"id":"68","itemName":"苹果肉","itemCategory":"湿垃圾"},{"id":"876","itemName":"苹果配件","itemCategory":"可回收垃圾"},{"id":"1417","itemName":"苹果周边","itemCategory":"可回收垃圾"},{"id":"2796","itemName":"苹果","itemCategory":"湿垃圾"},{"id":"2928","itemName":"苹果干","itemCategory":"湿垃圾"},{"id":"3067","itemName":"苹果梨","itemCategory":"湿垃圾"},{"id":"5812","itemName":"苹果耳机","itemCategory":"可回收垃圾"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 27
         * itemName : 拔丝苹果
         * itemCategory : 湿垃圾
         */

        private String id;
        private String itemName;
        private String itemCategory;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public String getItemCategory() {
            return itemCategory;
        }

        public void setItemCategory(String itemCategory) {
            this.itemCategory = itemCategory;
        }
    }
}
