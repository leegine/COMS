head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorConstants.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �R�l�N�^�Ŏg���萔��`(WEB3RlsConnectorConstants.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/09 FLJ�V���@@�V�K�쐬
*/
package webbroker3.rlsgateway.connector;

/**
*
* �R�l�N�^�Ŏg���萔��`
* @@author Eizo Saito (FLJ)
* @@version 1.0
*/
public interface WEB3RlsConnectorConstants
{

    /**
     * ����ID�A�s��ID�Ɋ܂܂���ЃR�[�h�̌���
     */
    public static final int INSTITUTION_CODE_SIZE = 2;
    
    /**
     * �����t������ID�̌���
     */
    public static final int RLS_COND_ORDER_SIZE = 18;
    
    /**
     * xTier app region
     */
    public static final String XTIER_APP_REGION = "client";
    
    /**
     * xTier kernel config file
     */
    public static final String XTIER_KERNEL_FILE = "/config/xtier_kernel.xml";
    
    /**
     * class path key
     */
    public static final String JAVA_CLASS_PATH = "java.class.path";
    
    /**
     * path separator
     */
    public static final String PATH_SEPARATOR = "path.separator";
    
    /**
     * xTier retry sequential count
     */
    public static final String XTIER_RETRY_SEQUENTIAL_COUNT = "webbroker3.xtier.retry.sequential.count";
    
    /**
     * Default xTier retry sequential count
     */
    public static final long DEFAULT_XTIER_RETRY_SEQUENTIAL_COUNT = 5;
    
    /**
     * xTier retry interval
     */
    public static final String XTIER_RETRY_INTERVAL = "webbroker3.xtier.retry.interval";
    
    /**
     * Default xTier retry interval
     */
    public static final long DEFAULT_XTIER_RETRY_INTERVAL = 300000;
}
@
