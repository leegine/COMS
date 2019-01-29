head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	BooleanEnum.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 *
 * Copyright 2000-2002 Fitech Laboratories, Inc. All Rights Reserved.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. FITECH LABORATORIES AND ITS LICENSORS
 * SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
 * MODIFYING OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL
 * FITECH LABORATORIES OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT
 * OR DATA, OR FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE
 * DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT
 * OF THE USE OF OR INABILITY TO USE SOFTWARE, EVEN IF FITECH LABORATORIES HAS
 * BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 *
 */

package com.fitechlabs.xtrade.plugin.tc.gentrade;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

import java.io.*;
import java.util.*;


/**
 * <p>
 * Defines Boolean Enums.
 * </p>
 *
 * @@author x-zhang
 * @@version 1.0
 */
public class BooleanEnum extends Enumerated {

  	public static final BooleanEnum UNDEFINED = new BooleanEnum(IntValues.UNDEFINED,
      	"UNDEFINED");

  	public static final BooleanEnum TRUE = new BooleanEnum(IntValues.TRUE,
      	"TRUE");

  	public static final BooleanEnum FALSE = new BooleanEnum(IntValues.FALSE,
      	"FALSE");
    
    /**
   	 * Creates FinTransactionCateg object.
   	 * @@param i Int value of the Enum.
   	 * @@param s String representation of the Enum.
   	 */
  	public BooleanEnum(int i, String s) {
    	super(i, s);
  	}

  	/**
   	 * Optional Inner class to define values of integers used. In this way the
   	 * class can be easily used in switch statements
   	 */
  	public static class IntValues {

    	public static final int UNDEFINED = 99;

    	public static final int TRUE = 1;

    	public static final int FALSE = 0;

  	}
}
@
