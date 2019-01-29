head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���ʃ��N�G�X�g(WEB3MutualFixedBuyCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���M�莞��z���t���ʃ��N�G�X�g)<BR>
 * ���M�莞��z���t���ʃ��N�G�X�g<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyCommonRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_fixed_buy_common";
    
    /**
     * SerialVersionUID<BR>
     */   
    public final static long serialVersionUID = 200606261701L;

    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyCommonRequest.class);
   
    /**
     * (���M�莞��z���t���ʏ��ꗗ)<BR>
     * ���M�莞��z���t���ʏ��ꗗ<BR>
     */
    public WEB3MutualFixedBuyCommonUnit[] commonList;
   
    /**
     * (���M�莞��z���t���ʃ��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyCommonRequest()
    {
    }
  
    /** 
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>  
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>  
     * <BR>
     * �P�j���M�莞��z���t���ʏ��ꗗ�`�F�b�N <BR>
     * �@@�@@�P�|�P)�@@���M�莞��z���t���ʏ��ꗗ==null�̏ꍇ�A��O���X���[����B<BR>  
     * �@@�@@�@@�@@�@@�@@�@@�i���t�����ݒ�Ȃ��G���[�j <BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02474 <BR>
     * <BR>
     * �@@�@@�P�|�Q)�@@���M�莞��z���t���ʏ��ꗗ�̗v�f��==0���̏ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�i���t�����ݒ�Ȃ��G���[�j<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02474 <BR>
     * <BR>
     * �@@�@@�P�|�R)�@@���M�莞��z���t���ʏ��ꗗ�̗v�f�����J��Ԃ��ă`�F�b�N���s���B<BR> 
     * �@@�@@�@@�@@�P�|�R�|�P�j�����R�[�h == null �̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@(�����R�[�h���w��G���[�j<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_00079 <BR>
     * <BR>
     * �@@�@@�@@�@@�P�|�R�|�Q�j���t���z�i���X�j == null �̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���t���z���̓G���[�j<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02475 <BR>
     * <BR>
     * �@@�@@�@@�@@�P�|�R�|�R�j���t���z�i���X�j�������ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����`�F�b�N�G���[�j<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02476 <BR>
     * <BR>
     * �@@�@@�@@�@@�P�|�R�|�S�j���t���z�i���X�j �� 8���̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���t���z�i���X�j�����G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02477 <BR> 
     * <BR>
     * �@@�@@�@@�@@�P�|�R�|�T�j���t���z�i�ςݑ����j �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B<BR> 
     * �@@�@@�@@�@@�@@�@@�P�|�R�|�T�|�P�j���t���z�i�ςݑ����j�������ȊO�̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i�����`�F�b�N�G���[�j<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02478 <BR>
     * <BR>
     * �@@�@@�@@�@@�@@�@@�P�|�R�|�T�|�Q�j���t���z�i�ςݑ����j �� 9���̏ꍇ�A��O���X���[����B<BR> 
     * �@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@�i���t���z�i�ςݑ����j�����G���[�j<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@class: WEB3BusinessLayerException<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@tag:   BUSINESS_ERROR_02479 <BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);     
        
        //�P�j���M�莞��z���t���ʏ��ꗗ�`�F�b�N 
        //�@@�P�|�P)�@@���M�莞��z���t���ʏ��ꗗ==null�̏ꍇ�A��O���X���[����B
        if (this.commonList == null)
        {
            log.debug("���M�莞��z���t���ʏ��ꗗ==null�̏ꍇ�A" + 
                "��O���X���[����B");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02474,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "");
        }
        
        //�P�|�Q)�@@���M�莞��z���t���ʏ��ꗗ�̗v�f��==0���̏ꍇ�A��O���X���[����B
        if (this.commonList.length == 0)
        {
        	 log.debug("���M�莞��z���t���ʏ��ꗗ�̗v�f��==0���̏ꍇ�A" + 
                 "��O���X���[����B");
        	 log.exiting(STR_METHOD_NAME); 
        	 throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02474,
                 this.getClass().getName() + "." + STR_METHOD_NAME,
                 "");
        }
        
        //�P�|�R)�@@���M�莞��z���t���ʏ��ꗗ�̗v�f�����J��Ԃ��ă`�F�b�N���s���B
        //�@@�@@�@@�P�|�R�|�P�j�����R�[�h == null �̏ꍇ�A��O���X���[����B
        for (int i = 0; i < this.commonList.length; i++)
        {
            if (this.commonList[i].mutualProductCode == null)
            {
            	log.debug("�����R�[�h == null �̏ꍇ�A��O���X���[����B");
            	log.exiting(STR_METHOD_NAME); 
            	throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00079,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");       
            }
            
            //�P�|�R�|�Q�j���t���z�i���X�j == null �̏ꍇ�A��O���X���[����B
            if (this.commonList[i].monthlyBuyAmount == null)
            {
            	log.debug("���t���z�i���X�j == null �̏ꍇ�A��O���X���[����B");
            	log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02475,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");  
            }
            
            //�P�|�R�|�R�j���t���z�i���X�j�������ȊO�̏ꍇ�A��O���X���[����B
            if (!WEB3StringTypeUtility.isDigit(this.commonList[i].monthlyBuyAmount))
            {
            	log.debug("���t���z�i���X�j�������ȊO�̏ꍇ�A��O���X���[����B");
            	log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02476,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");  
            }
            
            //�P�|�R�|�S�j���t���z�i���X�j �� 8���̏ꍇ�A��O���X���[����B
            int l_intMonthlyBuyAmoutLen = this.commonList[i].monthlyBuyAmount.length();
            if (l_intMonthlyBuyAmoutLen >= 8)
            {
            	log.debug("���t���z�i���X�j �� 8���̏ꍇ�A��O���X���[����B");
            	log.exiting(STR_METHOD_NAME); 
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02477,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "");
            }
            
            //�P�|�R�|�T�j���t���z�i�ςݑ����j �� null �̏ꍇ�A�ȉ��̃`�F�b�N���s�Ȃ��B
            //�P�|�R�|�T�|�P�j���t���z�i�ςݑ����j�������ȊO�̏ꍇ�A��O���X���[����B
            if (this.commonList[i].increaseBuyAmount != null)
            {
               	if (!WEB3StringTypeUtility.isDigit(this.commonList[i].increaseBuyAmount))
               	{
            		log.debug("���t���z�i�ςݑ����j�������ȊO�̏ꍇ�A��O���X���[����B");
            		log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02478,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "");
               	}
               	
               	//�P�|�R�|�T�|�Q�j���t���z�i�ςݑ����j �� 9���̏ꍇ�A��O���X���[����B
               	int l_intIncreaseBuyAmountLen = this.commonList[i].increaseBuyAmount.length();
               	if (l_intIncreaseBuyAmountLen >= 9)
               	{
               		log.debug("���t���z�i�ςݑ����j �� 9���̏ꍇ�A��O���X���[����B");
               		log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02479,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "");
               	}
            }
        }
        log.exiting(STR_METHOD_NAME);             
    }
  
    /**
     *�icreateResponse�̎����j<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }        
}
@
