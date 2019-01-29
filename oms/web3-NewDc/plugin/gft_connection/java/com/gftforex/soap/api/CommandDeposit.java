head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.06.27.06.05.00;	author zhang-tengyu;	state dead;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	444;
commitid	8d84e081ce81473;
filename	CommandDeposit.java;

1.1
date	2011.03.15.02.44.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8b84d7ecf833551;
filename	CommandDeposit.java;


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

// original type: ['http://soap-api.gftforex.com']:CommandDeposit


public  class CommandDeposit 
  extends com.gftforex.soap.api.CommandTransferBase
  implements java.io.Serializable
{

  public CommandDeposit() {}

  public CommandDeposit(java.lang.String p_commandID
,     long p_accountID
,     double p_amount
,     java.lang.String p_currency) 
  {
    super(p_commandID,p_accountID,p_amount,p_currency);

  }









  public java.lang.String toString() {
  return "CommandDeposit" + "{"

    + " }";
  }

  public boolean equals(java.lang.Object __other__) {
    if (__other__ == this) return true;

    if (__other__ instanceof CommandDeposit) {
      if (!super.equals(__other__)) return false;

      CommandDeposit __obj__ =  (CommandDeposit) __other__;


      return true


      ;	
    }
    return false;
  }

  public int hashCode() {
    int __hash__result__ = 17;
    __hash__result__ = 37*__hash__result__ + super.hashCode() ;

    

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

