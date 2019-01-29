head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminRuitoTradeStopCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �ݓ������ʔ�����~�������N�G�X�g(WEB3AdminRuitoTradeStopCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/26 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�ݓ������ʔ�����~�������N�G�X�g)<BR>
 * �ݓ������ʔ�����~�������N�G�X�g�N���X
 */
public class WEB3AdminRuitoTradeStopCompleteRequest extends WEB3GenRequest
{

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_ruito_trade_stop_complete";
    
    /**
     * SerialVersionUID
     */
    public final static long serialVersionUID = 200408031539L;
    
    /**
     * (�����X�V���)<BR>
     * �����X�V���
     */
    public WEB3AdminRuitoUpdatedTradeInfo[] productUpdateInfoList;
    
    /**
     * (�I�y���[�V�������t)<BR>
     * �I�y���[�V�������t<BR>
     * ���͉�ʎ擾���̌��ݓ��t
     */
    public Date operationDate;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     * ���͉�ʎ擾���̌��ݓ��t
     */
    public String password;
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminRuitoTradeStopCompleteRequest.class); 
    
    /**
     * (validate())
     *�����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     *�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     *<BR>
     *�P�j�@@�����X�V���̃`�F�b�N<BR> 
�@@   *�|this.�����X�V���NULL�̏ꍇ�A��O���X���[����B<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01235<BR>
�@@   *�|this.�����X�V���̗v�f����0���̏ꍇ�A��O���X���[����B<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01236<BR>
�@@   *�|this.�����X�V���.�����R�[�h��NULL�̏ꍇ�A��O���X���[����B<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01237<BR>
�@@   *�|this.�����X�V���.���t�\�敪�i�����j��NULL�̏ꍇ�A��O���X���[����B<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01238<BR>
�@@   *�|this.�����X�V���.���\�敪�i�����j��NULL�̏ꍇ�A��O���X���[����B<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01239<BR>
�@@   *�|this.�����X�V���.���t�J�n��!=NULL����this.�����X�V���.���t�I����!=NULL�ł���A<BR> 
�@@�@@ *�����t�J�n�������t�I�����̏ꍇ�A��O���X���[����B <BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01240<BR>
�@@   *�|this.�����X�V���.���J�n��!=NULL����this.�����X�V���.���I����!=NULL�ł���A<BR> 
�@@�@@ *�����J�n�������I�����̏ꍇ�A��O���X���[����B <BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01241<BR>
     *<BR>
     *�Q�j�@@�I�y���[�V�������t�̃`�F�b�N<BR> 
�@@   *this.�I�y���[�V�������t��NULL�̏ꍇ�A��O���X���[����B<BR> 
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01242<BR>
     *<BR>
     *�R�j�@@�Ïؔԍ��̃`�F�b�N<BR> 
�@@   *�|this.�Ïؔԍ���NULL�̏ꍇ�A��O���X���[����<BR>
     *    class    : WEB3BusinessLayerException<BR>
     *    tag      : BUSINESS_ERROR_01090<BR>
     * @@throws WEB3BusinessLayerException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�����X�V���̃`�F�b�N 
        //�@@�|this.�����X�V���NULL�̏ꍇ�A��O���X���[����B 
        if(this.productUpdateInfoList == null)
        {
            log.debug("�����X�V��񂪖��w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01235,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�����X�V��񂪖��w��ł��B");              
        }
        
        //�@@�|this.�����X�V���̗v�f����0���̏ꍇ�A��O���X���[����B 
        if(this.productUpdateInfoList.length == 0)
        {
            log.debug("�����X�V���̗v�f�����O�ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01236,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�����X�V���̗v�f�����O�ł��B"); 
        }
        
        //�@@�|this.�����X�V���.�����R�[�h��NULL�̏ꍇ�A��O���X���[����B
        for (int i = 0; i < this.productUpdateInfoList.length; i++)
        {
            WEB3AdminRuitoUpdatedTradeInfo l_adminRuitoUpdatedTradeInfo = 
                    this.productUpdateInfoList[i];
            if (WEB3StringTypeUtility.isEmpty(
                    l_adminRuitoUpdatedTradeInfo.ruitoProductCode))
            {
                log.debug("�����X�V���.�����R�[�h�����͂���Ă��܂���B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01237,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�����X�V���.�����R�[�h�����͂���Ă��܂���B"); 
                
            }    
            
            //�@@�|this.�����X�V���.���t�J�n��!=NULL����this.�����X�V���.���t�I����!=NULL�ł���A 
            //�@@�@@�����t�J�n�������t�I�����̏ꍇ�A��O���X���[����B            
            Date l_datBuyStartDate = l_adminRuitoUpdatedTradeInfo.buyStartDate;
            Date l_datBuyEndDate = l_adminRuitoUpdatedTradeInfo.buyEndDate;
            
            if (l_datBuyStartDate != null && 
                l_datBuyEndDate != null && 
                WEB3DateUtility.compareToDay(l_datBuyStartDate, l_datBuyEndDate) >= 0)
            {
                log.debug("�����X�V���̔��t�J�n���͖����X�V���̔��t�I�����ȏ�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01240,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�����X�V���̔��t�J�n���͖����X�V���̔��t�I�����ȏ�ł��B");                 
            }
            
            //�@@�|this.�����X�V���.���J�n��!=NULL����this.�����X�V���.���I����!=NULL�ł���A 
            //�@@�@@�����J�n�������I�����̏ꍇ�A��O���X���[����B             
            Date l_datSellStartDate = l_adminRuitoUpdatedTradeInfo.sellStartDate;
            Date l_datSellEndDate = l_adminRuitoUpdatedTradeInfo.sellEndDate;
            
            if (l_datSellStartDate != null && 
                l_datSellEndDate != null && 
                WEB3DateUtility.compareToDay(l_datSellStartDate, l_datSellEndDate) >= 0)
            {
                log.debug("�����X�V���̉��J�n���͖����X�V���̉��I�����ȏ�ł��B");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01241,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "�����X�V���̉��J�n���͖����X�V���̉��I�����ȏ�ł��B");
            }
        }
        
        //�Q�j�@@�I�y���[�V�������t�̃`�F�b�N 
        //�@@this.�I�y���[�V�������t��NULL�̏ꍇ�A��O���X���[����B 
        if (this.operationDate == null)
        {
            log.debug("�I�y���[�V�������t�����͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01242,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�I�y���[�V�������t�����͂���Ă��܂���B");
        }

        //�R�j�@@�Ïؔԍ��̃`�F�b�N 
        //�@@�|this.�Ïؔԍ���NULL�̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.password))
        {
            log.debug("�Ïؔԍ������͂���Ă��܂���B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01090,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "�Ïؔԍ������͂���Ă��܂���B");
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �ݓ������ʔ�����~�������X�|���X�I�u�W�F�N�g�𐶐����A�ԋp����B<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 407297A702FB
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminRuitoTradeStopCompleteResponse(this);        
    }
}
@
