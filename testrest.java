/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 * Just a simple test of REST service, this service runs on a Rasperian pi
 * and light Payara web service.
 * @author Göran Lindqvist
 */
@Path("generic")
public class testrest {
    @Context
    private UriInfo context;
    private final ExecutorService executorService = java.util.concurrent.Executors.newCachedThreadPool();
    JSONObject jObj = new JSONObject();
    JSONArray jArr = new JSONArray();
    
    /**
     * Creates a new instance of GenericResource
     */
    public testrest() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/test/{testArg}")  
    public Response getTest(@PathParam("testArg") String arg) throws UnsupportedOperationException {
        try {
            String s1 = (URLDecoder.decode(arg, "UTF-8"));
            jArr.add(s1);
            jObj.put("testFkn", jArr);
            //TODO return proper representation object
         return Response.status(Response.Status.ACCEPTED).entity("Value: " +jObj.toJSONString()).build();   
//        throw new UnsupportedOperationException();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testrest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

/**
 * getMult
 * @param asyncResponse
 * @param tal1
 * @param tal2 
 */    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path(value = "mult/{tal1}/{tal2}")
    public void getMult(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "tal1") final String tal1, @PathParam(value = "tal2") final String tal2) {
        executorService.submit(() -> {
            asyncResponse.resume(doGetMult(tal1, tal2));
        });
    }
/**
 * doGetMult
 * @param tal1, string
 * @param tal2, string
 * @return json
 */
    private Response doGetMult(@PathParam("tal1") String tal1, @PathParam("tal2") String tal2) {
        try {
            String sS1 = (URLDecoder.decode(tal1, "UTF-8"));
            String sS2 = (URLDecoder.decode(tal2, "UTF-8"));
            jArr.add(multMath(sS1, sS2));
            jObj.put("produkten", jArr);
            return  Response.status(Response.Status.ACCEPTED).entity(jObj.toJSONString()).build();
        } catch (UnsupportedEncodingException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error in getMult: " + ex.getMessage()).build();
        }
    }

/**
 * getSub
 * @param asyncResponse
 * @param tal1
 * @param tal2
 */    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path(value = "/sub/{tal1}/{tal2}")
    public void getSub(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "tal1") final String tal1, @PathParam(value = "tal2") final String tal2) {
        executorService.submit(() -> {
            asyncResponse.resume(doGetSub(tal1, tal2));
        });
    }
/**
 * doGetSub
 * @param tal1
 * @param tal2
 * @return json 
 */
    private Response doGetSub(@PathParam("tal1") String tal1, @PathParam("tal2") String tal2) {
        try{
            String sS1 = (URLDecoder.decode(tal1, "UTF-8"));
            String sS2 = (URLDecoder.decode(tal2, "UTF-8"));
            jArr.add(subMath(sS1, sS2));
            jObj.put("skillnaden", jArr);
            return Response.status(Response.Status.ACCEPTED).entity(jObj.toJSONString()).build();
        }catch(UnsupportedEncodingException ex){
            return Response.status(Response.Status.BAD_REQUEST).entity("Error in getSub: " + ex.getMessage()).build();
        }
    }

/**
 * getAdd
 * @param asyncResponse
 * @param tal1, string
 * @param tal2, string
 */    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path(value = "/add/{tal1}/{tal2}")
    public void getAdd(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "tal1") final String tal1, @PathParam(value = "tal2") final String tal2) {
        executorService.submit(() -> {
            asyncResponse.resume(doGetAdd(tal1, tal2));
        });
    }
/**
 * doGetAdd
 * @param tal1, string
 * @param tal2, string
 * @return json 
 */
    private Response doGetAdd(@PathParam("tal1") String tal1, @PathParam("tal2") String tal2) {
        try {
            String sS1 = (URLDecoder.decode(tal1, "UTF-8"));
            String sS2 = (URLDecoder.decode(tal2, "UTF-8"));
            jArr.add(addMath(sS1, sS2));
            jObj.put("summan", jArr);
            return Response.status(Response.Status.ACCEPTED).entity(jObj.toJSONString()).build();
        } catch (UnsupportedEncodingException ex) {
            // Logger.getLogger(testrest.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Error in getAdd: " + ex.getMessage()).build();
        }
    }

/**
 * getDiv
 * @param asyncResponse
 * @param t1, string
 * @param t2, string
 */    
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Path(value = "/div/{tal1}/{tal2}")
    public void getDiv(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "tal1") final String t1, @PathParam(value = "tal2") final String t2) {
        executorService.submit(() -> {
            asyncResponse.resume(doGetDiv(t1, t2));
        });
    }
/**
 * doGetDiv
 * @param t1, string
 * @param t2, string
 * @return json
 */
    private Response doGetDiv(@PathParam("tal1") String t1, @PathParam("tal2") String t2) {
        try {
            String sS1 = (URLDecoder.decode(t1, "UTF-8"));
            String sS2 = (URLDecoder.decode(t2, "UTF-8"));
            jArr.add(divMath(t1, t2));
            jObj.put("skillnaden", jArr);
            //TODO return proper representation object
            return Response.status(Response.Status.ACCEPTED).entity(jObj.toJSONString()).build();
//        throw new UnsupportedOperationException();
        } catch (UnsupportedEncodingException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error in getDiv: " + ex.getMessage()).build();
        }
    }    
    
    /**
     * addMath
     * @param t1, string
     * @param t2, string
     * @return string 
     */
    private String addMath(String t1, String t2){
        try {
            int iT1, iT2;
            iT1 = Integer.parseInt(t1);
            iT2 = Integer.parseInt(t2);
            String sSum = Integer.toString(iT1+iT2);
            return  sSum;
        } catch (Exception e) {
            return "Error in addMath:" +e.getMessage();
        }
    }

    /**
     * subMath
     * @param t1, string
     * @param t2, string
     * @return  string
     */
    private String subMath(String t1, String t2){
        try{
            int iT1, iT2;
            iT1 = Integer.parseInt(t1);
            iT2 = Integer.parseInt(t2);
            String sSub = Integer.toString(iT1-iT2);
            return sSub;
        }catch(Exception e){
            return "Error in subMath: " +e.getMessage();
        }
    }

    /**
     * multMath
     * @param t1, string
     * @param t2, string
     * @return  string
     */
    private String multMath(String t1, String t2){
        try{
            int iT1, iT2;
            iT1 = Integer.parseInt(t1);
            iT2 = Integer.parseInt(t2);
            String sMult = Integer.toString(iT1*iT2);
            return sMult;
        }catch(Exception e){
            return "Error in multMath: " +e.getMessage();
        }
    }

    /**
     * divMath
     * @param t1, string
     * @param t2, string
     * @return string
     */
    private String divMath(String t1, String t2){
        try{
            if(t2.equals("0")){
                return "Error in divMath: zero in denominator";
            }            
            double iT1, iT2;
            iT1 = Double.parseDouble(t1);
            iT2 = Double.parseDouble(t2);
            String sDiv =  Double.toString(iT1/iT2); 
            return sDiv;
        }catch(Exception e){
            return "Error in divMath: " +e.getMessage();
        }
    }
}
