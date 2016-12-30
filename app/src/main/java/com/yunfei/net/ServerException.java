package com.yunfei.net;

/**
 * Created by yunfei on 2016/12/30.
 * email mayunfei6@gmail.com
 */

public class ServerException extends Exception {
  private int code;

  public ServerException(int code) {
    this.code = code;
  }

  @Override public String getMessage() {
    String msg = "";
    switch (code) {
      case 10001:
        msg = "错误的请求KEY";
        break;
      case 10002:
        msg = "该KEY无请求权限";
        break;
      case 10003:
        msg = "KEY过期";
        break;
      case 10004:
        msg = "错误的OPENID";
        break;
      case 10005:
        msg = "应用未审核超时，请提交认证";
        break;
      case 10008:
        msg = "被禁止的IP";
        break;
      case 10009:
        msg = "被禁止的KEY";
        break;
      case 100011:
        msg = "当前IP请求超过限制";
        break;
      case 100012:
        msg = "请求超过次数限制";
        break;
      case 100013:
        msg = "测试KEY超过请求限制";
        break;
      case 100014:
        msg = "系统内部异常";
        break;
      case 100020:
        msg = "接口维护";
        break;
      case 100021:
        msg = "接口停用";
        break;
      default:
        msg = "服务器错误";
    }

    return msg;
  }
}
