head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoProspectusServiceResultDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �i�d�q���V�X�e���ԋp�l�j�ژ_�����{���`�F�b�N�@@�萔��`�C���^�t�F�C�X(WEB3GentradeBatoProspectusServiceResultDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 ����(�r�q�`) �V�K�쐬
*/
package webbroker3.gentrade.define;

/**
 * �i�d�q���V�X�e���j�ژ_�����{���`�F�b�N�T�[�r�X�߂�l�@@�萔��`�C���^�t�F�C�X<br />
 * <br />
 * �y�ژ_�����{���`�F�b�N�T�[�r�X�z<br />
 * �^�[�Q�b�g�l�[���X�y�[�X�FBato_HistCheck_Service<br />
 * �T�[�r�X���FBatoProspectusService<br />
 * ���\�b�h���FchkHistry<br />
 * 
 */
public interface WEB3GentradeBatoProspectusServiceResultDef
{
    /**
     * 0�F �������� 
     */
    public static final String HISTORY = "0";
    
    /**
     * 1�F �����Ȃ�
     */
    public static final String NO_HISTORY = "1";
    
    /**
     * 2�F �����Ǘ��Ȃ� 
     */
    public static final String CHECK_OFF = "2";
    

    /**
     * InvalidCompCode�F ��ЃR�[�h�t�H�[�}�b�g�G���[ 
     */
    public static final String ERR_INSTITUTION_CODE = "InvalidCompCode";
    
    /**
     * InvalidBranCode�F ���X�R�[�h�t�H�[�}�b�g�G���[ 
     */
    public static final String ERR_BRANCH_CODE = "InvalidBranCode";
    
    /**
     * InvalidCustCode�F �ڋq�R�[�h�t�H�[�}�b�g�G���[ 
     */
    public static final String ERR_ACCOUNT_CODE = "InvalidCustCode";

    /**
     * InvalidTypeCode�F ��ʃR�[�h�t�H�[�}�b�g�G���[ 
     */
    public static final String ERR_TYPE_CODE = "InvalidTypeCode";

    /**
     * InvalidFundCode�F �����R�[�h�t�H�[�}�b�g�G���[ 
     */
    public static final String ERR_PRODUCT_CODE = "InvalidFundCode";

    /**
     * AuthenticationError�F �F�؃G���[ 
     */
    public static final String ERR_AUTHENTICATE = "AuthenticationError";

    /**
     * InvalidBussinessTime�F ��t���ԊO 
     */
    public static final String ERR_TRADING_TIME = "InvalidBussinessTime";

    /**
     * NoProspectus�F �ژ_�������o�^ 
     */
    public static final String ERR_NO_PROSPEC = "NoProspectus";

    /**
     * DBError�F DB�G���[ 
     */
    public static final String ERR_DB_ACCESS = "DBError";

}
@
