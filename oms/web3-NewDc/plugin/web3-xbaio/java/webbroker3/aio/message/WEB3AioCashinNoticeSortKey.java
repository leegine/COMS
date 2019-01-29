head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.10.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeSortKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�\�[�g�L�[�N���X(WEB3AioCashinNoticeSortKey)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 ��O�� (���u) �V�K�쐬
                 : 2006/08/23  �Ԑi(���u) �d�l�ύX ���f�� 614
*/

package webbroker3.aio.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * �����ʒm�\�[�g�L�[�N���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeSortKey extends Message 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashinNoticeSortKey.class);    
    
    /**
     * (�\�[�g�L�[)<BR>
     */
    public String keyItem;
    
    /**
     * (�����^�~��)<BR>
     * A:�����@@D:�~��<BR>
     */
    public String ascDesc;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h�i�񖼁j<BR>
     */
    private String BRANCH_CODE = "branch_code";
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�i�񖼁j<BR>
     */
    private String ACCOUNT_CODE = "account_code";
    
    /**
     * (�����)<BR>
     * ������i�񖼁j<BR>
     */
    private String SETTLEMENT_DATE = "deposit_data_account_date";
    
    /**
     * (�U���˗��l�R�[�h)<BR>
     * �U���˗��l�R�[�h�i�񖼁j<BR>
     */
    private String CLIENT_CODE = "deposit_data_trans_person_code";
    
    /**
     * (��s�R�[�h)<BR>
     * ��s�R�[�h�i�񖼁j<BR>
     */
    private String BANK_CODE = "bank_code";    
    
    /**
     * (�x�X�R�[�h)<BR>
     * �x�X�R�[�h�i�񖼁j<BR>
     */
    private String BANK_BRANCH_CODE = "bank_branch_code";
    
    /**
     * (�����敪)<BR>
     * �����敪�i�񖼁j<BR>
     */
    private String TRANSACTION_DIV = "status";
    
    /**
     * (��������)<BR>
     * ���������i�񖼁j<BR>
     */
    private String TRANSACTION_DATE = "last_updated_timestamp";
    
    /**
     * (�ʉ݃R�[�h )<BR>
     * �ʉ݃R�[�h�i�񖼁j<BR>
     */
    private String CURRENCY_CODE = "currency_code";
    
    /**
     * �R���X�g���N�^�B <BR>
     * <BR>
     *�P�jthis.�\�[�g�L�[�Ɉ���.�L�[���ڂ��Z�b�g����B<BR>
     *�Q�jthis.�����^�~���Ɉ���.�����^�~�����Z�b�g����B<BR>
     * @@param String  - (�L�[����)
     * @@param String  - (�����^�~��)
     * @@roseuid 40A9B8B4003A
     */
    public WEB3AioCashinNoticeSortKey()
    {
       
    }   
    
    /**
     * �\�[�g�L�[��null�łȂ��ꍇ <BR>
     * <BR>
     * this.���X�R�[�h <BR>
     * this.�ڋq�R�[�h <BR>
     * this.�����<BR>
     * this.�U���˗��l�R�[�h <BR>
     * this.��s�� <BR>
     * this.�x�X�� <BR>
     * this.�����敪 <BR>
     * this.�������� <BR>
     * this.�ʉ݃R�[�h <BR>
     * �̂��Â�ɂ��������Ȃ��ꍇ <BR>
     * <BR>
     * �u�L�[���ڂɍ��ږ��ȊO�̒l�����݃G���[�v <BR>
     * ��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 40A9B8B4003A
     */
    public void validate() throws WEB3BaseException 
    {        
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�\�[�g�L�[��null�łȂ��ꍇ 

        //this.���X�R�[�h 
        //this.�ڋq�R�[�h 
        //this.������R�[�h 
        //this.�U���˗��l�R�[�h 
        //this.��s�� 
        //this.�w�薼 
        //this.�����敪 
        //this.�������� 
        //this.�ʉ݃R�[�h
        //�̂��Â�ɂ��������Ȃ��ꍇ 

        //�u�L�[���ڂɍ��ږ��ȊO�̒l�����݃G���[�v 
        //��O���X���[����B 

        if (this.keyItem != null)
        {
            log.debug("�L�[���� = " + this.keyItem);
            
            if (!this.BRANCH_CODE.equals(this.keyItem) &&
                !this.ACCOUNT_CODE.equals(this.keyItem) && 
                !this.SETTLEMENT_DATE.equals(this.keyItem) && 
                !this.CLIENT_CODE.equals(this.keyItem) &&
                !this.BANK_CODE.equals(this.keyItem) &&
                !this.BANK_BRANCH_CODE.equals(this.keyItem) &&
                !this.TRANSACTION_DIV.equals(this.keyItem) && 
                !this.TRANSACTION_DATE.equals(this.keyItem) &&
                !this.CURRENCY_CODE.equals(this.keyItem))
            {
                log.debug("�L�[���ڂɍ��ږ��ȊO�̒l�����݃G���[");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�L�[���ڂɍ��ږ��ȊO�̒l�����݃G���[");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *�\�[�g�L�[��<BR>
     *�����^�~���w���t�����\�[�g��������쐬�A�ԋp����B<BR>
     *<BR>
     *�P�j�\�[�g�L�[��<BR>
     *�����^�~�������w��܂��͏����^�~����WEB3AscDescDef.ASC�Ɠ������ꍇ��" asc",<BR>
     *WEB3AscDescDef.DSC�Ɠ������ꍇ " desc"��t������B<BR>
     *<BR>
     *�Q�j�쐬�����\�[�g�����������Ԃ��B<BR>
     *���\�[�g�L�[��null�̏ꍇ��null��Ԃ��B<BR>
     * @@return String
     * @@roseuid 40A9B8B4003A
     */
    public String createSortKeySpec() 
    {        
        final String STR_METHOD_NAME = "createSortKeySpec()";
        log.entering(STR_METHOD_NAME);
        
        String l_strSortKeySpec = null;
        
        //�P�j�\�[�g�L�[��
        //�����^�~�������w��܂��͏����^�~����WEB3AscDescDef.ASC�Ɠ������ꍇ��" asc",
        //WEB3AscDescDef.DSC�Ɠ������ꍇ " desc"��t������B
        
        if (this.keyItem != null)
        {
            if (this.ascDesc == null || WEB3AscDescDef.ASC.equals(this.ascDesc))
            {
                l_strSortKeySpec = this.keyItem + " asc";
            }
            else
            {
                l_strSortKeySpec = this.keyItem  + " desc";
            }
        }

        //�Q�j�@@�쐬�����\�[�g�����������Ԃ��B 
        //���\�[�g�L�[��null�̏ꍇ��null��Ԃ��B�B
        
        log.exiting(STR_METHOD_NAME);
        return l_strSortKeySpec;
    }
}
@
