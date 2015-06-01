//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruhua.domain.common;

public enum ErrorConstant {
    PROCESS_SUCCESS("1", "成功"),
    PROCESS_FAIL("2", "失败"),
    PROCESS_WARNING("3", "警告"),
    SYSTEM_ERROR("-1", "系统异常"),
    PARAM_ERROR("-2", "参数异常"),
    PRESORTING_TRANS("1000", "预分拣错误"),
    PRESORTING_ORDER_ALREADY_EXISTED("1001", "人工预分拣订单已经存在"),
    PRESORTING_RECEIVE_TASKS_ERROR("1002", "领取预分拣任务出现业务操作异常"),
    PRESORTING_SUBMIT_TASKS_ERROR("1003", "提交预分拣任务出现业务操作异常"),
    PRESORTING_SUBMIT_PARAM_NULL("1004", "提交人工预分拣结果，前台传入参数为空"),
    PRESORTING_PAGE_QUERY_ERROR("1005", "分页查询人工预分拣单有业务异常"),
    PRESORTING_TRANSFER_EXCEPTION_STATUS_ERROR("1006", "将人工预分拣单转异常时出现业务异常"),
    AUTO_PRESORTING_LOCATE_LOG_QUERY("1007", "预分拣日志查询异常"),
    RESORTING_RECEIVE_TASKS_ERROR("1501", "领取返调度分拣任务出现业务操作异常"),
    RESORTING_SUBMIT_TASKS_ERROR("1502", "提交返调度分拣任务出现业务操作异常"),
    RESORTING_PAGE_QUERY_ERROR("1503", "分页查询返调度单有业务异常"),
    RESORTING_TRANSFER_EXCEPTION_STATUS_ERROR("1504", "将返调度单转异常时出现业务异常"),
    AFS_SERVICE_QUERY("2000", "售后服务单管理查询出错"),
    AFS_SERVICE_APPLY("2001", "申请售后服务单出错"),
    AFS_SERVICE_SERVICEDETAIL_QUERY("2002", "获取售后服务单审核明细出错"),
    AFS_SERVICE_UPDATE("2003", "审核售后服务单更新数据失败"),
    AFS_SERVICE_UPDATE_HANDELSOLUTION("2004", "调用已解决接口失败"),
    AFS_SERVICE_QUDITSUCCESS("2005", "调用审核同过接口失败"),
    AFS_SERVICE_AUDITABORTED("2006", "调用审核不通过接口失败"),
    AFS_SERVICE_GETREFUND("2007", "获取售后服务单金额失败"),
    AFS_SERVICE_REWORKFOROTO("2008", "调用换新接口失败"),
    AFS_SERVICE_REFUNDFOROTO("2009", "调用退款接口失败"),
    AFS_SERVICE_HANDELSOLUTION("2010", "调用已解决接口失败"),
    AFS_SERVICE_TIMERATE_REPORT("2011", "调用审核、处理及时率接口失败"),
    ORDER_SERVICE_QUERY("3000", "订单列表查询出错"),
    ORDER_DETAIL_QUERY("3001", "订单详细内容查询出错"),
    ORDER_UPDATE_INFO("3002", "更新订单详细内容出错"),
    ORDER_LOCK_INFO("3003", "查询订单门店锁定前状态出错"),
    ORDER_RESEND("3004", "订单再投出错"),
    CHECK_GET_ORDER_RIGHT("3005", "检查用户是否有权限查询订单出错"),
    UPDATE_ORDER_EXPORT("3006", "更新订单为已出库出错"),
    ORDER_SEND_FINISH("3007", "更新订单妥投出错"),
    ORDER_CANCEL("3008", "更新订单取消出错"),
    ORDER_LIST_QUERY_TANGJIU("3009", "唐久订单列表查询出错"),
    ORDER_STATION_RECEIVE_ERROR("3010", "门店收单出现业务异常"),
    ORDER_ANTIDISTRIBUTE("3011", "更新订单返调度出错"),
    ORDER_STATION_SEND_PHONE_MSG_ERROR("3012", "门店收单发送短信失败"),
    ORDER_IS_NOT_EXISTED("3013", "订单不存在"),
    ORDER_NOT_BELONG_STATION_ERROR("3014", "订单不属于门店"),
    ORDER_UPDATE_STATUS_ERROR("3015", "更新订单状态出错"),
    ORDER_STATUS_ILLEGAL_ERROR("3016", "订单状态不合法"),
    ORDER_INFO_SYN("3017", "查询当前调度服务器可处理的补全订单信息出错"),
    ORDER_UPDATE_DELIVERY_NO_ERROR("3018", "更新订单表货运单号出错！"),
    QUERY_DELIVERY_NO_ERROR("3019", "查询修改快运单号出错！"),
    QUERY_PRODUCT_ERROR("3020", "查询修改快运单号出错！"),
    PRODUCT_INFO_SKU("3021", "查询需要补全商家SKU编号列表出错"),
    AREA_SERVICE_QUERY("4000", "获取地址信息出错"),
    AREA_SYNC("4001", "四级地址信息同步出错"),
    SKU_RELATION_QUERY("4002", "获取商品关联关系出错"),
    SKU_RELATION_SAVE("4003", "保存商品关联关系出错"),
    SKU_RELATION_DELETE("4004", "删除商品关联关系出错"),
    SELLER_AREA_ADD("4005", "新增销售区域出错"),
    SELLER_AREA_DELETE("4006", "删除销售区域出错"),
    SELLER_AREA_QUERY("4007", "查询销售区域出错"),
    SKU_DEIPLAY_QUERY("4008", "商品展示查询出错"),
    SKU_RELATION_PRICE("4009", "同步商品关联价格出错"),
    STOCK_OWNER_QUERY("4010", "获取商品库存归属出错"),
    STORE_DELIVERY_QUERY("4011", "查询门店配送服务信息出错"),
    STORE_DELIVERY_QUERY_NO_RESULT("4012", "没有找到门店配送服务信息"),
    STORE_SUPPORT_NOT_FOUND("4013", "没有找到门店支援库房"),
    STORE_NOT_FOUND("4014", "没有找到相关门店"),
    QUERY_LOGISTICS_COMPANY("4015", "查询物流公司信息出错"),
    DICT_SERVICE_QUERY("5555", "数据字典查询出错"),
    RESEND_REPORT_QUERY("6001", "组织再投订单报表数据出错"),
    RESEND_REPORT_DETAIL("6002", "获取再投订单明细数据出错"),
    DALIY_FUNDS_GET("6003", "获取日帐款汇总数据出错"),
    HEAD_REPORT_GET("6004", "组织总部报表数据出错"),
    STORE_IS_NOT_EXISTED("6005", "门店不存在"),
    ORDER_TIMEOUT_ERROR("6006", "查询订单超时数据时出错"),
    DELIVERY_SUCC_RATE_ERROR("6007", "计算配送成功率出现异常"),
    AFSSERVICE_DELAY_REPORT("6008", "售后超时服务单报表查询出错"),
    UPDATE_STOCK("7001", "更新门店库存信息异常"),
    QUERY_STORE_DELIVERY("8001", "查询门店配送信息出错!"),
    QUERY_STANDARD_DELIVERY("8002", "查询店铺信息出错!"),
    SAVE_STANDARD_DELIVERY("8003", "保存店铺信息出错!"),
    UPDATE_STANDARD_DELIVERY("8004", "修改店铺信息出错!"),
    UPDATE_STANDARD_DELIVERY_BYKEY("8005", "根据主键修改店铺信息出错!"),
    SAVE_UPDATE_DELIVERY("8006", "保存或修改店铺信息出错!"),
    QUERY_STANDARD_DELIVERY_PAGE("8007", "查询店铺信息出错!"),
    QUERY_EXPORT_PAGE("8008", "查询店铺信息出错!"),
    QUERY_CREATE_PAGE_FORPRINT("9001", "出库单生成有误!");

    private String errorCode;
    private String errorMsg;

    private ErrorConstant(String errorCode, String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}
