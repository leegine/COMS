head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.56.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t�����ꗗ���N�G�X�g(WEB3BondApplyBuyProductListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.bd.define.WEB3BondReferenceTypeDef;

/**
 * (������/���t�����ꗗ���N�G�X�g)<BR>
 * ������/���t�����ꗗ���N�G�X�g<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondApplyBuyProductListRequest extends WEB3GenRequest 
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondApplyBuyProductListRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyProductList";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L;        
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (�Ɖ�敪)<BR>
     * �Ɖ�敪<BR>
     * <BR>
     * 1�F����ꗗ<BR>
     * 2�F���t�ꗗ<BR>
     * 3�F����/���t�ꗗ<BR>
     */
    public String referenceType;
    
    /**
     * (�v���y�[�W�ԍ�)<BR>
     * �v���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (�y�[�W���\���s��)<BR>
     * �y�[�W���\���s��<BR>
     */
    public String pageSize;
    
    /**
     * @@roseuid 44FBFD390203
     */
    public WEB3BondApplyBuyProductListRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�@@�Ɖ�敪�`�F�b�N<BR>
     * �@@�P�|�P�j�@@�Ɖ�敪==null�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00081<BR>
     * �@@�P�|�Q�j�@@�Ɖ�敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@1�F ����ꗗ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@2�F ���t�ꗗ<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@3�F ����/���t�ꗗ<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00082<BR>
     * <BR>
     * �Q�j�@@�v���y�[�W�ԍ��`�F�b�N<BR> 
     * �@@�Q�|�P�j�@@�v���y�[�W�ԍ�==null�̏ꍇ�A��O���X���[����B<BR> 
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00089<BR>
     * �@@�Q�|�Q�j�@@�v���y�[�W�ԍ��������ȊO�̏ꍇ�A��O���X���[����B<BR> 
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00090<BR>
     * <BR>
     * �R�j�@@�y�[�W���\���s���`�F�b�N<BR> 
     * �@@�R�|�P�j�@@�y�[�W���\���s��==null�̏ꍇ�A��O���X���[����B<BR> 
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02224<BR>
     * �@@�R�|�Q�j�@@�y�[�W���\���s���������ȊO�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00092<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44B6E1B3029F
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�Ɖ�敪�`�F�b�N 
        //�@@�P�|�P�j�@@�Ɖ�敪==null�̏ꍇ�A��O���X���[����B 
        if (this.referenceType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪�����w��ł��B");
        }
        
        //�@@�P�|�Q�j�@@�Ɖ�敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B 
        //�@@�@@�@@�@@�@@�@@�@@�@@1�F ����ꗗ 
        //�@@�@@�@@�@@�@@�@@�@@�@@2�F ���t�ꗗ 
        //�@@�@@�@@�@@�@@�@@�@@�@@3�F ����/���t�ꗗ 
        else if (!(WEB3BondReferenceTypeDef.RECRUIT_LIST.equals(this.referenceType)
            || WEB3BondReferenceTypeDef.BUY_LIST.equals(this.referenceType)
            || WEB3BondReferenceTypeDef.RECRUIT_BUY_LIST.equals(this.referenceType)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�Ɖ�敪�̒l�����݂��Ȃ��R�[�h�l�ł��B");
        }
        
        //�Q�j�@@�v���y�[�W�ԍ��`�F�b�N  
        //�@@�Q�|�P�j�@@�v���y�[�W�ԍ�==null�̏ꍇ�A��O���X���[����B
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //�@@�Q�|�Q�j�@@�v���y�[�W�ԍ��������ȊO�̏ꍇ�A��O���X���[����B
        else if (!WEB3StringTypeUtility.isDigit(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�R�j�@@�y�[�W���\���s���`�F�b�N  
        //�@@�R�|�P�j�@@�y�[�W���\���s��==null�̏ꍇ�A��O���X���[����B
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        
        //�@@�R�|�Q�j�@@�y�[�W���\���s���������ȊO�̏ꍇ�A��O���X���[����B
        else if (!WEB3StringTypeUtility.isDigit(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
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
        return new WEB3BondApplyBuyProductListResponse(this);
    }
}
@
