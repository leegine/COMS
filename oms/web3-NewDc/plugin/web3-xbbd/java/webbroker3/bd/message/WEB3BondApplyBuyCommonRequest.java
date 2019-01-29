head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.44.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t���ʃ��N�G�X�g(WEB3BondApplyBuyCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (������/���t���ʃ��N�G�X�g)<BR>
 * ������/���t���ʃ��N�G�X�g<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondApplyBuyCommonRequest extends WEB3GenRequest 
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondApplyBuyCommonRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyCommon";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L; 
    
    /**
     * (����敪)<BR>
     * ����敪<BR>
     * <BR>
     * �P�F����<BR>
     * �Q�F���t<BR>
     */
    public String tradeDiv;
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String productId;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 1�F�~��<BR>
     * 2�F�O��<BR>
     */
    public String settleDiv;
    
    /**
     * (�z�ʋ��z)<BR>
     * �z�ʋ��z<BR>
     */
    public String faceAmount;
    
    /**
     * (�Љ�敪)<BR>
     * �Љ�敪<BR>
     * <BR>
     * 1�F���ڎ��<BR>
     * 2�F�P���Љ�<BR>
     * 3�F���i�Љ�<BR>
     * 4�F������<BR>
     */
    public String introduceStoreDiv;
    
    /**
     * (�Љ�X�R�[�h)<BR>
     * �Љ�X�R�[�h<BR>
     */
    public String introduceStoreCode;
    
    /**
     * @@roseuid 44FBFD38038A
     */
    public WEB3BondApplyBuyCommonRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�@@����敪�`�F�b�N<BR>
     * �@@�P�|�P�j�@@����敪 == null�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00601<BR>
     * �@@�P�|�Q�j�@@����敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@1�F ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@2�F ���t<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00602<BR>
     * <BR>
     * �Q�j�@@����ID�`�F�b�N <BR>
     * �@@�@@�@@����ID == null�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02229 <BR>
     * <BR>
     * �R�j�@@���ϋ敪�`�F�b�N<BR> 
     * �@@�R�|�P�j�@@���ϋ敪 == null�̏ꍇ�A��O���X���[����B<BR> 
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02111<BR>
     * �@@�R�|�Q�j�@@���ϋ敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@1�F�~��<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@2�F�O��<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02112<BR>
     * <BR>
     * �S�j�@@�z�ʋ��z�`�F�b�N<BR>
     * �@@�S�|�P�j�@@�z�ʋ��z == null�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02634<BR>
     * �@@�S�|�Q�j�@@�z�ʋ��z �������ł͂Ȃ��ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02641<BR>
     * �@@�S�|�R�j�@@�z�ʋ��z �� 0�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02636<BR>
     * �@@�S�|�S�j�@@�z�ʋ��z �� 11���̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02635<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44C857C40297
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����敪�`�F�b�N 
        //�@@�P�|�P�j�@@����敪 == null�̏ꍇ�A��O���X���[����B 
        if (this.tradeDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00601,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����敪�����w��ł��B");
        }
        
        //�@@�P�|�Q�j�@@����敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B 
        //�@@�@@�@@�@@�@@�@@�@@�@@1�F ���� 
        //�@@�@@�@@�@@�@@�@@�@@�@@2�F ���t 
        else if (!(WEB3BondDealDivDef.RECRUIT.equals(this.tradeDiv)
            || WEB3BondDealDivDef.BUY.equals(this.tradeDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����敪�����݂��Ȃ��R�[�h�l�ł��B");
        }

        //�Q�j�@@����ID�`�F�b�N  
        //�@@�@@�@@����ID == null�̏ꍇ�A��O���X���[����B
        if (this.productId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }

        //�R�j�@@���ϋ敪�`�F�b�N  
        //�@@�R�|�P�j�@@���ϋ敪 == null�̏ꍇ�A��O���X���[����B  
        if (this.settleDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02111,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ϋ敪��null�ł��B");
        }
        
        //�@@�R�|�Q�j�@@���ϋ敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B 
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@1�F�~�� 
        //�@@�@@�@@�@@�@@�@@�@@�@@�@@2�F�O�� 
        else if (!(WEB3SettlementDivDef.JAPANESE_CURRENCY.equals(this.settleDiv)
            || WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(this.settleDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02112,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "���ϋ敪������`�̒l�ł��B");
        }        

        //�S�j�@@�z�ʋ��z�`�F�b�N 
        //�@@�S�|�P�j�@@�z�ʋ��z == null�̏ꍇ�A��O���X���[����B 
        if (this.faceAmount == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02634,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z�����w��ł��B");
        }
        
        //�@@�S�|�Q�j�@@�z�ʋ��z �������ł͂Ȃ��ꍇ�A��O���X���[����B
        else if (!WEB3StringTypeUtility.isInteger(this.faceAmount))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02641,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z�������l�ł͂���܂���B");
        }
        
        //�@@�S�|�R�j�@@�z�ʋ��z �� 0�̏ꍇ�A��O���X���[����B 
        else if (Double.parseDouble(this.faceAmount) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02636,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z��0�ȉ��̒l�ł��B");
        }
        
        //�@@�S�|�S�j�@@�z�ʋ��z �� 11���̏ꍇ�A��O���X���[����B
        else if (WEB3StringTypeUtility.getByteLength(this.faceAmount) > 11)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02635,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�z�ʋ��z�̃T�C�Y���s���ł��B");
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
        return null;
    }
}
@
