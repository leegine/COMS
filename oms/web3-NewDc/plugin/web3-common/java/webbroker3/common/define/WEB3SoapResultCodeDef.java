head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.38.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3SoapResultCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��t���ʃR�[�h�iSOAP�j�萔��`�C���^�t�F�C�X(WEB3SoapResultCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/01 ������(���u) �V�K�쐬
Revesion History : 2008/09/09 ������ (���u) �d�l�ύX�c�a���C�A�E�gNo.144
*/
package webbroker3.common.define;

/**
 * ��t���ʃR�[�h�iSOAP�j�萔��`�C���^�t�F�C�X
 *
 * @@author ������(���u)
 * @@version 1.0
 */
public interface WEB3SoapResultCodeDef                
{
    /**
     *  0: ����I��
     */
    public static final String  NORMAL = "0";
    
    /**
     *  1: ����I���i�����������t�s�j
     */
    public static final String  NORMAL_CANNOT_IN = "1";

    /**
     *  2: �p�����^�G���[
     */
    public static final String  PARAM_ERROR = "2";
    
    /**
     *  3: �d���G���[
     */
    public static final String  REPEAT_ERROR = "3";

    /**
     *  1001: ���p�҃R�[�h�s��
     */
    public static final String  USER_CODE_ERROR = "1001";

    /**
     *  1003: �ڋq�R�[�h�s��
     */
    public static final String  ACCOUNT_CODE_ERROR = "1003";
    
    /**
     *  3007: �d���o�^�G���[
     */
    public static final String  DUP_SUBMIT_ERROR = "3007";
    
    /**
     *  3008: �ȖڃR�[�h�s��
     */
    public static final String  SUBJECT_CODE_ERROR = "3008";

    /**
     *  3009: �����z�s��
     */
    public static final String  IN_AMOUNT_ERROR = "3009";

    /**
     *  6001: �ғ����ԊO�G���[
     */
    public static final String  WORK_TIME_OUT_ERROR = "6001";

    /**
     *  27: ���̑��G���[�j
     */
    public static final String  OTHER_ERROR = "27";
 
    /**
     *  9991: �ڑ��G���[�i�V�X�e���G���[�j
     */
    public static final String  CONNECT_ERROR = "9991";

    /**
     *  9992: �ڑ��^�C���A�E�g�G���[�i�V�X�e���G���[�j
     */
    public static final String  CONNECT_TIME_OUT_ERROR = "9992";
}
@
