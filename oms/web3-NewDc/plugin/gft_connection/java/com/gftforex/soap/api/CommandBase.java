head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.07.22;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CommandBase.java;

1.1
date	2011.03.15.02.41.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CommandBase.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
 * This code was automatically generated at 17:28:54 on 2008/10/30
 * by weblogic.xml.schema.binding.internal.codegen.BeanImplGenerator -- do not edit.
 *
 * @@version WebLogic Temporary Patch for CR112756 03/08/2004 13:53:04
 * @@author Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
 */

package com.gftforex.soap.api;

// original type: ['http://soap-api.gftforex.com']:CommandBase


public  class CommandBase 
  
  implements java.io.Serializable
{

  public CommandBase() {}

  public CommandBase(java.lang.String p_commandID) 
  {
     this.commandID = p_commandID;
     

  }




  
  private java.lang.String commandID ;

  /**
  <br>  Derived from CommandID.

  <br>  schema name = ['http://soap-api.gftforex.com']:CommandID
  <br>  schema type = ['http://soap-api.gftforex.com']:CommandID
  <br>  schema formQualified = true
  */
  public java.lang.String getCommandID() {
    return commandID;
  }

  public void setCommandID(java.lang.String v) {
    
    this.commandID = v;
    
  }










  public java.lang.String toString() {
  return "CommandBase" + "{"
             + " commandID=<" + getCommandID() + ">"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CommandBase) {
      
      CommandBase __obj__ =  (CommandBase) __other__;


      return true
            && (commandID==null ? __obj__.getCommandID()==null : commandID.equals(__obj__.getCommandID()))


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;

    __hash__result__ = 37*__hash__result__ + (commandID==null ? 0 : commandID.hashCode()) ;
    

    return __hash__result__;
  }

}


@


1.1
log
@*** empty log message ***
@
text
@@

