head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.48.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioTelegramFormatDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �萔��`�C���^�t�F�C�X(WEB3AioTelegramFormatDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 ����� (���u) �V�K�쐬
                   2006/04/14 �юu�� (���u)�@@�d�l�ύX�E���f��527
*/
package webbroker3.aio.define;

/**
 * �������v��fromat�ƌ��ϊJ�n�v��format
 * �ƌ��ό��ʒʒmformat�̃L�[�@@�萔��`�C���^�t�F�C�X
 *                                                                     
 * @@author �����
 * @@version 1.0
 */
public interface WEB3AioTelegramFormatDef 
{
    /**
     * �v���g�R���o�[�V����   protocolVersion
     */
    public static final String protocolVersion = "protocolVersion";

    /**
     * �������� date
     */
    public static final String date = "date";

    /**
     * .com�f�r�b�g���ώ���ԍ�   centerPayId
     */
    public static final String centerPayId = "centerPayId";

    /**
     * ���^�[���R�[�h  returnCode
     */
    public static final String returnCode = "returnCode";

    /**
     * �x�����敪    ComOndebiPayMode
     */
    public static final String ComOndebiPayMode = "ComOndebiPayMode";

    /**
     * ��\���i����   ComOndebiTypicalGoodsName
     */
    public static final String ComOndebiTypicalGoodsName = "ComOndebiTypicalGoodsName";

    /**
     * ������z amount
     */
    public static final String amount = "amount";

    /**
     * �ŁA���� ComOndebiTax
     */
    public static final String ComOndebiTax = "ComOndebiTax";

    /**
     * �G���[�\�����e  description
     */
    public static final String description = "description";

    /**
     * �L�����Z��URL cancelURL
     */
    public static final String cancelURL = "cancelURL";

    /**
     * �G���[URL   errorURL
     */
    public static final String errorURL = "errorURL";

    // =========remain zhou-yong NO.1 begin ========
    /**
     * PR�w�Z�b�V�����L�[   prsid
     */
    public static final String prsid = "prsid";
    
    /**
     * PR�w�A�v���P�[�V����ID   praid
     */
    public static final String praid = "praid";
    
    /**
     * PR�w�Đ����T�[�r�XID   praarsid
     */
    public static final String praarsid = "praarsid";
    
    /**
     * PR�wSSID  prssid
     */
    public static final String prssid = "prssid";
    
    /**
     * PR�wDPDV  prdpdv
     */
    public static final String prdpdv = "prdpdv";
    
    /**
     * io_rturl
     */
    public static final String io_rturl = "io_rturl";
    
    /**
     * aa_aid
     */
    public static final String aa_aid = "aa_aid";

    /**
     * aa_rsid
     */
    public static final String aa_rsid = "aa_rsid";

    /**
     * ssid
     */
    public static final String ssid = "ssid";
    
    /**
     * prdpdv
     */
    public static final String aa_dpdv = "aa_dpdv";
    
    // =========remain zhou-yong NO.1 end ========
    
    /**
     * PR�w�Z�b�V����ID   wolfSessionKey
     */
    public static final String wolfSessionKey = "wolfSessionKey";

    /**
     * AP�w�Z�b�V����ID   apsid
     */
    public static final String apsid = "apsid";

    /**
     * �،���ЃR�[�h  cpy
     */
    public static final String cpy = "cpy";

    /**
     * ���X�R�[�h    btn
     */
    public static final String btn = "btn";

    /**
     * �ڋq�R�[�h    acc
     */
    public static final String acc = "acc";
    
    /**
     * ���ʃR�[�h    req
     */
    public static final String req = "req";
    
    /**
     * �����o�H�敪    rdiv
     */
    public static final String rdiv = "rdiv";

    /**
     * WEB3���N�G�X�g�R�[�h web3Request
     */
    public static final String web3Request = "web3Request";

    /**
     * �����ԍ� linked_1
     */
    public static final String linked_1 = "linked_1";

    /**
     * �����XID    shopId
     */
    public static final String shopId = "shopId";

    /**
     * ���ϋ@@��ID   paySchemeId
     */
    public static final String paySchemeId = "paySchemeId";

    /**
     * �A�N�Z�X�L�[   accessKey
     */
    public static final String accessKey = "accessKey";

    /**
     * ���ϋ@@��ID   pSId
     */
    public static final String pSId = "pSId";

    /**
     * ���^�[��URL  returnURL
     */
    public static final String returnURL = "returnURL";

    /**
     * PF�������ʔ�  ComOndebiSalesSlip
     */
    public static final String ComOndebiSalesSlip = "ComOndebiSalesSlip";

    /**
     * PF����������  ComOndebiAuthDate
     */
    public static final String ComOndebiAuthDate = "ComOndebiAuthDate";

    /**
     * ������� payStatus
     */
    public static final String payStatus = "payStatus";

    /**
     * ���F�ԍ� ComOndebiAuthresCode
     */
    public static final String ComOndebiAuthresCode = "ComOndebiAuthresCode";

    /**
     * �U�������\���  ComOndebiCaptureDate
     */
    public static final String ComOndebiCaptureDate = "ComOndebiCaptureDate";

    /**
     * �����[�g�A�h���X remoteAdd
     */
    public static final String remoteAdd = "remoteAdd";
}
@
