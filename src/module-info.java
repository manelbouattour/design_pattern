/**
 * 
 */
/**
 * 
 */
module Projet {
	requires javafx.graphics;
	 requires javafx.controls;
	requires java.sql;
	
	 opens main to javafx.graphics; 
	    exports main;
}