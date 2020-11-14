/**failed*/
//package com.connect_us.backend.security.config;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.util.Collections;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
///**소셜 로그인시 타 사이트에서 인증 후 우리 서비스에 로그인 시켜야 됨
// * db에서 찾은 사용자 정보를  httpservletRequest로 넣어서 로그인 시킨 다음 jwt 생성
// * 이때 httpservletRequest는 setParameter가 없기 때문에 변경하는 클래스를 따로 만들었음
// * */
//public class ModifiableHttpServletRequest extends HttpServletRequestWrapper {
//    private HashMap<String, Object> params;
//    /**
//     * Constructs a request object wrapping the given request.
//     *
//     * @param request The request to wrap
//     * @throws IllegalArgumentException if the request is null
//     */
//    public ModifiableHttpServletRequest(HttpServletRequest request) {
//        super(request);
//        this.params = new HashMap<String, Object>(request.getParameterMap());
//    }
//
//    public String getParameter(String name){
//        String val=null;
//        String[] paramArray = getParameterValues(name);
//        if(paramArray!=null && paramArray.length>0){
//            val=paramArray[0];
//        }
//        return val;
//    }
//
//    @SuppressWarnings("unchecked")
//    public Map getParameterMap(){
//        return Collections.unmodifiableMap(params);
//    }
//
//    @SuppressWarnings("unchecked")
//    public Enumeration getParameterNames(){
//        return Collections.enumeration(params.keySet());
//    }
//
//    public String[] getParameterValues(String name){
//        String[] res = null;
//        String[] temp = (String[])params.get(name);
//        if(temp!=null){
//            res = new String[temp.length];
//            System.arraycopy(temp,0,res,0,temp.length);
//        }
//        return res;
//    }
//
//    public void setParameter(String name, String value){
//     params.put(name,value);
//    }
//}
