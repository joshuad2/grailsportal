package com.grailsPortal.exception

	public class ValidationException extends Exception{
		public Object exceptionVal
		public ValidationException(){
			exceptionVal=null
		}
		public ValidationException(Object val){
			exceptionVal=val
		}
		public Object getExceptionVal(){
			return exceptionVal
		}
	}
