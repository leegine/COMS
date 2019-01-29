head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���c���Ɖ�N�G�X�g(WEB3BondBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ��іQ (���u) �V�K�쐬
Revesion History : 2007/03/09 ꎉ�   (���u) �d�l�ύX�E���f��160
Revesion History : 2007/07/17 ���g   (���u) �d�l�ύX�E���f��206
*/

package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondBalanceReferenceDetailUnitDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���c���Ɖ�N�G�X�g)<BR>
 * ���c���Ɖ�N�G�X�g�N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceRequest extends WEB3BondBalanceReferenceCommonRequest
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceRequest.class);
    
    /**
     *�@@PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference";

    /**
     *�@@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609201900L;
    
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

    public WEB3BondSortKey[] sortKeys;
    
    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondBalanceReferenceRequest()
    {
        
    }
    
    /**
     * ���N���X�̐������`�F�b�N���s���B<BR>
     *�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�[�g�L�[�̃`�F�b�N<BR>
     * �@@�P�|�P�j�\�[�g�L�[ == null�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[��null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00231<BR> 
     * <BR>
     * �@@�P�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A<BR>
     * �@@�@@�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00232<BR>
     * <BR>
     * �@@�P�|�R�j�\�[�g�L�[�̔z��̌����A<BR>
     * �@@�@@�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B<BR>  
     * �@@�@@�P�|�R�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������B<BR>  
     * �@@�@@�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ�<BR>  
     * �@@�@@�@@�ȉ��̍��ږ��ȊO�����݂����ꍇ�A<BR>  
     * �@@�@@�@@�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B<BR>  
     * �@@�@@�@@�@@�E���c���Ɖ��.������ <BR>
     * �@@�@@�@@�@@�E���c���Ɖ��.���p�\���� <BR>
     * �@@�@@�@@�@@�E���c���Ɖ��.�ʉ�<BR> 
     * �@@�@@�@@�@@�E���c���Ɖ��.�T�Z�]���z�i�~�݁j<BR> 
     * �@@�@@�@@�@@�E���c���Ɖ��.�T�Z�]���z�i�O�݁j<BR> 
     * �@@�@@�@@�@@�E���c���Ɖ��.���s��<BR> 
     * �@@�@@�@@�@@�E���c���Ɖ��.���ғ�<BR> 
     * ���h�����h�̃\�[�g���́h�������h�����N�G�X�g�Ƃ��đ����Ă���B<BR>�@@
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00086<BR>�@@�@@ 
     * <BR>
     * �Q�j�v���y�[�W�ԍ��`�F�b�N <BR> 
     * �@@�Q�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A<BR>  
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00089<BR>  
     * <BR>
     * �@@�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A<BR>  
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B<BR> 
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00090<BR> 
     * <BR>
     * �@@�Q�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A<BR>  
     * �@@�@@�@@�@@�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B<BR>  
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00616<BR>
     * <BR>
     * �R�j�y�[�W���\���s���`�F�b�N<BR>  
     * �@@�R�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A<BR>  
     * �@@�@@�@@�@@�u�y�[�W���\���s����null�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_02224<BR>  
     * <BR>
     * �@@�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A<BR>  
     * �@@�@@�@@�@@�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00092<BR>  
     * <BR>�@@  
     * �@@�R�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A<BR>  
     * �@@�@@�@@�@@�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@class:�@@WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@tag:�@@�@@BUSINESS_ERROR_00617<BR> 
     * <BR>
     * �S�j�X�[�p�[�N���X��validate�i�j���R�[������B<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�[�g�L�[�̃`�F�b�N  
        //�P�|�P�j�\�[�g�L�[ == null�̏ꍇ�A  
        //�u�\�[�g�L�[��null�v�̗�O���X���[����B
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�����w��ł��B");
        }
        
        //�P�|�Q�j�\�[�g�L�[�̗v�f����0�̏ꍇ�A  
        //�u�\�[�g�L�[�̗v�f����0�v�̗�O���X���[����B
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�\�[�g�L�[�̗v�f�����O�ł��B");
        }
        
        //�P�|�R�j�\�[�g�L�[�̔z��̌����A�J��Ԃ��Ĉȉ��̃`�F�b�N���s���B
        int l_intSortKeyLength = this.sortKeys.length;  
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //�P�|�R�|�P�j�\�[�g�L�[.validate()���\�b�h���R�[������
            sortKeys[i].validate();
            
            //�P�|�R�|�Q�j�\�[�g�L�[.�L�[���ڂ�  
            //�ȉ��̍��ږ��ȊO�����݂����ꍇ�A  
            //�u�\�[�g�L�[�̃L�[���ڂ�����`�̒l�v�̗�O���X���[����B  
            //�E���c���Ɖ��.������ 
            //�E���c���Ɖ��.���p�\���� 
            //�E���c���Ɖ��.�ʉ� 
            //�E���c���Ɖ��.�T�Z�]���z�i�~�݁j 
            //�E���c���Ɖ��.�T�Z�]���z�i�O�݁j 
            //�E���c���Ɖ��.���s�� 
            //�E���c���Ɖ��.���ғ� 
            // ���h�����h�̃\�[�g���́h�������h�����N�G�X�g�Ƃ��đ����Ă���B
            if (!WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_NAME.equals(sortKeys[i].keyItem) && 
                !WEB3BondBalanceReferenceDetailUnitDef.SELL_ABLE_QTY.equals(sortKeys[i].keyItem) && 
                !WEB3BondBalanceReferenceDetailUnitDef.CURRENCY_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3BondBalanceReferenceDetailUnitDef.YEN_ESTIMATED_ASSET.equals(sortKeys[i].keyItem) &&
                !WEB3BondBalanceReferenceDetailUnitDef.FOREIGN_ESTIMATED_ASSET.equals(sortKeys[i].keyItem) &&
                !WEB3BondBalanceReferenceDetailUnitDef.ISSUE_DATE.equals(sortKeys[i].keyItem) &&
                !WEB3BondBalanceReferenceDetailUnitDef.MATURITY_DATE.equals(sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "�\�[�g�L�[�̃L�[���ڂ̒l�����݂��Ȃ��R�[�h�l�ł��B");
            }               
        }
        
        //�Q�j�v���y�[�W�ԍ��`�F�b�N  
        // �Q�|�P�jthis.�v���y�[�W�ԍ� == null�ł������ꍇ�A  
        // �u�v���y�[�W�ԍ���null�v�̗�O���X���[����B  
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ������w��ł��B");
        }
        
        //�Q�|�Q�jthis.�v���y�[�W�ԍ��������ȊO�̒l�ł������ꍇ�A  
        //�u�v���y�[�W�ԍ��������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��������ȊO�̒l�ł��B");
        }
        
        //�Q�|�R�jthis.�v���y�[�W�ԍ����O�ł������ꍇ�A  
        //�u�v���y�[�W�ԍ���0�ȉ��v�̗�O���X���[����B 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�v���y�[�W�ԍ��̒l��0�ȉ��ł��B");
        }
        
        //�R�j�y�[�W���\���s���`�F�b�N  
        //�R�|�P�jthis.�y�[�W���\���s�� == null�ł������ꍇ�A  
        //�u�y�[�W���\���s����null�v�̗�O���X���[����B 
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������͂ł��B");
        }
        
        //�R�|�Q�jthis.�y�[�W���\���s���������ȊO�̒l�ł������ꍇ�A  
        //�u�y�[�W���\���s���������ȊO�v�̗�O���X���[����B
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���������ȊO�̒l�ł��B");
        }
        
        //�R�|�R�jthis.�y�[�W���\���s�����O�ł������ꍇ�A  
        //�u�y�[�W���\���s����0�ȉ��v�̗�O���X���[����B
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�y�[�W���\���s���̒l��0�ȉ��ł��B");
        }

        //�S�j�X�[�p�[�N���X��validate�i�j���R�[������B
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }
      
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3BondBalanceReferenceResponse(this);
    }
}
@
