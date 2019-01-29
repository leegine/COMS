head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.51.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �،��U�֋��ʃ��N�G�X�g(WEB3AioSecurityTransferCommomRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 ���z   (���u) �V�K�쐬 
                   2006/11/03 �����q (���u) ���f��No.678 
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.aio.define.WEB3AioMessageCommodityDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�،��U�֋��ʃ��N�G�X�g)<BR>
 * �،��U�֋��ʃ��N�G�X�g�N���X
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferCommonRequest extends WEB3GenRequest 
{
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412071537L;     
    
    /**
    * ���O�o�̓��[�e�B���e�B�B
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferCommonRequest.class);
    
    /**
     * (���i�^�C�v)<BR>
     * �����̏��i�^�C�v<BR>
     * <BR>
     * 1�F ����<BR>
     * 2�F ��<BR>
     * 3�F �����M��<BR>
     */
    public String instrumentsType;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F ���<BR>
     * 1�F ����<BR>
     */
    public String taxType;
    
    /**
     * (�a��敪)<BR>
     * �U�֌��a��敪<BR>
     * <BR>
     * 1�F �ی�i�ی삩���p�ւ̐U�ցj<BR>
     * 2�F ��p�i��p����ی�ւ̐U�ցj
     */
    public String depositDiv;
    
    /**
     * @@roseuid 41B0255E0232
     */
    public WEB3AioSecurityTransferCommonRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���i�^�C�v<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.���i�^�C�v != (1, 2, 3)<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01295<BR> 
     * <BR>
     * �Q�j�����R�[�h<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�����R�[�h = null or<BR>
     *   ���N�G�X�g�f�[�^.�����R�[�h != ���p����<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00079<BR> 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00815<BR> 
     * <BR>
     * �R�j�����敪<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�����敪 != (0, 1)<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01303<BR> 
     * <BR>
     * �S�j�a��敪<BR>
     * <BR>
     *   ���N�G�X�g�f�[�^.�a��敪 != (1, 2)<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01297<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4153D8210102
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        //�P�j���i�^�C�v
        //  ���N�G�X�g�f�[�^.���i�^�C�v != (1, 2, 3)
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01295 
        if (!(WEB3AioMessageCommodityDef.EQUITY.equals(this.instrumentsType) ||
            WEB3AioMessageCommodityDef.BOND.equals(this.instrumentsType) ||
            WEB3AioMessageCommodityDef.MUTUAL_FUND.equals(this.instrumentsType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01295,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.���i�^�C�v != (1, 2, 3), " +
                "���N�G�X�g�f�[�^.���i�^�C�v = " + this.instrumentsType);               
        }

        //�Q�j�����R�[�h
        //  ���N�G�X�g�f�[�^.�����R�[�h = null or
        //  ���N�G�X�g�f�[�^.�����R�[�h != ���p����
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00079 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00815 
        if (WEB3StringTypeUtility.isEmpty(this.productCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�����R�[�h = null");              
        }
        else if(!WEB3StringTypeUtility.isNumber(this.productCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�����R�[�h != ���p����, " +
                "���N�G�X�g�f�[�^.�����R�[�h = " + this.productCode);                
        }
        
        //�R�j�����敪
        //  ���N�G�X�g�f�[�^.�����敪 != (0, 1)
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01303 
        if (!(WEB3AccountDivDef.NORMAL.equals(this.taxType) || 
            WEB3AccountDivDef.SPECIAL.equals(this.taxType)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01303,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�����敪 != (0, 1)," +
                "���N�G�X�g�f�[�^.�����敪 = " + this.taxType);             
        }
        
        //�S�j�a��敪
        //  ���N�G�X�g�f�[�^.�a��敪 != (1, 2)
        //  �̏ꍇ�A��O���X���[����B
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01297    
        if (!(WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(this.depositDiv) ||
            WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(this.depositDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01297,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�a��敪 != (1, 2), " +
                "���N�G�X�g�f�[�^.�a��敪 = " + this.depositDiv);                  
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * �،��U�֋��ʃ��X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return null;    
    }
}
@
