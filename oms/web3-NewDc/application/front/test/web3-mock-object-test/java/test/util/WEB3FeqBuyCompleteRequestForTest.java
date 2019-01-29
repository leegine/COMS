head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.26.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqBuyCompleteRequestForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package test.util;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3FeqBuyCompleteRequestForTest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_buyComplete";

    /**
     * SerialVersionUID<BR>
     */
   public static final long serialVersionUID = 200611301602L;   
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String orderId;
    
    /**
     * (�m�F���P��)<BR>
     * �m�F���P��<BR>
     */
    public String checkPrice;
    
    /**
     * (�m�F��������)<BR>
     * �m�F��������<BR>
     */
    public Date checkDate;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FeqBuyCompleteRequestForTest.class);
    
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = ".validate()";
        log.entering(STR_METHOD_NAME);
        
        // �Q�j�����R�[�h�`�F�b�N 
        // this.����ID == null �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.orderId)) {
            log.debug("����ID�����w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + STR_METHOD_NAME,
                "����ID = " + this.orderId);
        }
        
        // �R�j�m�F���P���`�F�b�N 
        // this.�m�F���P�� == null �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.checkPrice)) {
            log.debug("�m�F���P�������w��ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00206,
                this.getClass().getName() + STR_METHOD_NAME,
                "�m�F���P�� = " + this.checkPrice);
        }
        
        // �S�j�m�F���������`�F�b�N 
        // this.�m�F�������� == null �̏ꍇ�A��O���X���[����B
        if (this.checkDate == null) {
            log.debug("�m�F�������������͂���Ă��܂���B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + STR_METHOD_NAME,
                "�m�F�������� = " + this.checkDate);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
