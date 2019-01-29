head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.47.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondSellConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����p�m�F���N�G�X�g(WEB3BondSellConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����p�m�F���N�G�X�g)<BR>
 * �����p�m�F���N�G�X�g<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondSellConfirmRequest extends WEB3GenRequest 
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondSellConfirmRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_sellConfirm";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;   
    
    /**
     * �ۗL���YID<BR>
     */
    public String id;
    
    /**
     * (�z�ʋ��z)<BR>
     * �z�ʋ��z<BR>
     */
    public String faceAmount;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 1�F�~��<BR>
     * 2�F�O��<BR>
     */
    public String settleDiv;
    
    /**
     * @@roseuid 44FBFD3902AF
     */
    public WEB3BondSellConfirmRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR> 
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_01919<BR> 
     * <BR>
     * �Q�j�@@�z�ʋ��z�`�F�b�N<BR>
     * �@@�Q�|�P�j�@@this.�z�ʋ��z�������ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02641<BR>
     * �@@�Q�|�Q�j�@@this.�z�ʋ��z���O�ȉ��̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02636<BR>
     * <BR>
     * �R�j�@@���ϋ敪�`�F�b�N<BR>
     * �@@this.���ϋ敪���ȉ���`�l�ȊO�̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@1�F�~��<BR>
     * �@@�@@2�F�O��<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02112<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D7FC3801CD
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�h�c�`�F�b�N  
        //�@@this.�h�c��null�̒l�ł���Η�O���X���[����B  
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�ۗL���YID�����w��(null)�ł��B");            
        }
        
        //�Q�j�@@�z�ʋ��z�`�F�b�N 
        //�@@�Q�|�P�j�@@this.�z�ʋ��z�������ł͂Ȃ��ꍇ�A��O���X���[����B 
        if (!WEB3StringTypeUtility.isInteger(this.faceAmount))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02641,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z�������l�ł͂���܂���B"); 
        }
        
        //�@@�Q�|�Q�j�@@this.�z�ʋ��z���O�ȉ��̏ꍇ�A��O���X���[����B 
        else if (Double.parseDouble(this.faceAmount) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02636,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z��0�ȉ��̒l�ł��B");
        }

        //�R�j�@@���ϋ敪�`�F�b�N 
        //�@@this.���ϋ敪���ȉ���`�l�ȊO�̏ꍇ�A��O���X���[����B 
        //�@@�@@1�F�~�� 
        //�@@�@@2�F�O��
        if (!(WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)
            || WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02112,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ϋ敪������`�̒l�ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondSellConfirmResponse(this);
    }
}
@
