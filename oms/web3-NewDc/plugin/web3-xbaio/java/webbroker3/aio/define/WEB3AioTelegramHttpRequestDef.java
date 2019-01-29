head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.50.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTelegramHttpRequestDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AioTelegramHttpRequestDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ����� (���u) �V�K�쐬
*/
package webbroker3.aio.define;

/**
 * �}���`�o���N�d�������T�[�r�XImpl��web3Request�̃L�[�l�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����
 * @@version 1.0
 */
public interface WEB3AioTelegramHttpRequestDef
{

    /**
     * OrderDemand �����v����t  
     */
    public static final String ORDERDEMAND = "OrderDemand";

    /**
     * SettleStart ���ϊJ�n��t  
     */
    public static final String SETTLE_START = "SettleStart";

    /**
     * SettleResult ���ό��ʒʒm  
     */
    public static final String SETTLE_RESULT = "SettleResult";
    
    /**
     * 127.0.0.1 �d�����s���̃����[�g�A�h���X  
     */
    //public static final String REMOTE_ADDRESS = "127.0.0.1";
    public static final String REMOTE_ADDRESS = "_REMOTE_ADDRESS";

    /*
     * �^�C���A�E�g
     */
    public static final String AIO_TIMEOUT = "_AIO_TIMEOUT";
    
    // ========remain zhou-yong NO.1 begin ==========
    
    /**
     * URL �������L�����Z�����ꂽ�ꍇ�A�G���[�����������ꍇ�ɌĂяo�����WEB3��URL   
     */
    //public static final String URL = "[URL]";
    //public static final String URL = "https://trade.wb3dev.jp/webbroker3/Web3App";
    //public static final String URL = "https://trade.retela.co.jp/webbroker3/Web3App";

    /**
     *  0   
     */
    public static final String IO_RTURL_ADDRESS0 = "0";
    
    /**
     *  1   
     */
    public static final String IO_RTURL_ADDRESS1 = "1";
    
    /**
     *  2   
     */
    public static final String IO_RTURL_ADDRESS2 = "2";  
    
    /**
     * URL ���������������ꍇ�A�G���[�����������ꍇ�ɌĂяo�����WEB3��URL 
     */
    //public static final String COMPLETE_URL = "[URL]";
    //public static final String COMPLETE_URL = "https://trade.wb3dev.jp/webbroker3/Web3App";
    //public static final String COMPLETE_URL = "https://trade.retela.co.jp/webbroker3/Web3App";
    
    public static final String WEB3_URL = "_WEB3_URL";
    
    // ========remain zhou-yong NO.1 end ==========
    
    /**
     * PF_URL ����������PF��URL  
     */
    //public static final String PF_URL = "PF_URL";
    //public static final String PF_URL = "http://172.17.186.49/webbroker/web2web/web3OrderDemand.html";
    //public static final String PF_URL = "https://www.safetydebit2.jp/asp/user/payment/servlet/E10AspFuriwakeSvlt";
    public static final String PF_URL = "_PF_URL";
    
    /**
     *�@@��M�d���f�[�^.get("payStatus")�̖߂�l��"START" �̏ꍇ
     */
    public static final String START = "START";
    
    /**
     *�@@��M�d���f�[�^.get("payStatus")�̖߂�l��"ERROR" �̏ꍇ
     */
    public static final String ERROR = "ERROR";

    /**
     * ��M�d���f�[�^.get("protocolVersion")�̖߂�l��"V1.0" �̏ꍇ 
     */
    public static final String V1DOT0 = "V1.0";

    /**
     * ���X�|���X�f�[�^��ContentType: text/plain 
     */
    public static final String ContentType = "text/plain; charset=Shift_JIS";

    /**
     * ���X�|���X�̃��b�Z�[�W�{�f�B START TAG: <SHOPMSG>
     */
    public static final String SHOPMSG_START = "<SHOPMSG>";

    /**
     * ���X�|���X�̃��b�Z�[�W�{�f�B END TAG: <SHOPMSG>
     */
    public static final String SHOPMSG_END = "</SHOPMSG>";
}
@
