head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.02.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyApplyConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�V�K�\���m�F���N�G�X�g(WEB3MutualFixedBuyApplyConfirmRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (���M�莞��z���t�V�K�\���m�F���N�G�X�g) <BR>
 * ���M�莞��z���t�V�K�\���m�F���N�G�X�g <BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyApplyConfirmRequest 
    extends WEB3MutualFixedBuyCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_apply_confirm";
    
    /**
     * SerialVersionUID<BR>
     */   
    public final static long serialVersionUID = 200606261700L;

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyApplyConfirmRequest.class);

    /**
     * (���M�莞��z���t�V�K�\���m�F���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */   
    public WEB3MutualFixedBuyApplyConfirmRequest()
    {
    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>  
     * <BR>
     * �P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B<BR> 
     * <BR>
     * �Q)�@@���M�莞��z���t���ʏ��ꗗ�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR> 
     * �@@�Q�|�P�j���t���z�i���X�j== 0 �̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�i���t���z���̓G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02475 <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {   
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);    
         
        //�P�j�@@�X�[�p�[�N���X��validate���\�b�h���Ăяo���B
        super.validate();
        
        // �Q)�@@���M�莞��z���t���ʏ��ꗗ�̗v�f�����J��Ԃ��ă`�F�b�N���s���B       
        //�@@�Q�|�P�j���t���z�i���X�j== 0 �̏ꍇ�A��O���X���[����B      
        int l_intcommonListLength = this.commonList.length; 
        for (int i = 0; i < l_intcommonListLength; i++)
        {
        	String l_strMonthlyBuyAmount = this.commonList[i].monthlyBuyAmount;
        	double l_dblMonthlyBuyAmount = Double.parseDouble(l_strMonthlyBuyAmount);
            if (l_dblMonthlyBuyAmount == 0)
            {
                log.debug("���t���z�i���X�j== 0 �̏ꍇ�A��O���X���[����B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
	                WEB3ErrorCatalog.BUSINESS_ERROR_02475,
	                this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);  
    }
   
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * ���M�莞��z���t�V�K�\���m�F���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualFixedBuyApplyConfirmResponse(this);
    }
}
@
