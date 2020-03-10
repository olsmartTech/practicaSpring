package com.nucleoti.searching.core.info.model.constantes;

public class UtilsConsultas {
	public static final String CALL_AUTENTICACION = "call SP_AUTENTICACIONCUENTA(?,?,?,?)";
	public static final String CALL_EXISTENCIA_EMAIL="call SP_EXISTENCIA_EMAIL(?,?)";
	public static final String CALL_CREATE_CUENTA="call SP_REGISTRA_CUENTA(?,?,?,?,?,?,?)";
	public static final String CALL_LIST_PRODUCT="{call fn_list_product(?)}";
	public static final String CALL_LIST_PRODUCT_BY_ID="{call fn_list_product_by_id(?,?)}";
}
