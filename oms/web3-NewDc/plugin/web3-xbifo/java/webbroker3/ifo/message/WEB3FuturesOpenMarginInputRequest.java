head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K���������͉�ʃ��N�G�X�g�N���X(WEB3FuturesOpenMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;

/**
 * (�����w���敨�V�K���������͉�ʃ��N�G�X�g)<BR>
 * �����w���敨�V�K���������͉�ʃ��N�G�X�g�N���X<BR>
 * 
 * @@author ������
 * @@version 1.0 
 */
public class WEB3FuturesOpenMarginInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_OpenMarginInput";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201655L;

    /**
     * (�����R�[�h)<BR>
     * �X�����O�V���b�N�����A�����w��̏ꍇ<BR>
     */
    public String futProductCode;

    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;

    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     * <BR>
     * �����I����ʂ��J�ڎ��Ɏw�肳���<BR>
     */
    public String marketCode;

    /**
     * (�w�����)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     * <BR>
     * �����I����ʂ��J�ڎ��Ɏw�肳���<BR>
     */
    public String targetProductCode;

    /**
     * (����)<BR>
     * YYYYMM�`��<BR>
     * <BR>
     * �����I����ʂ��J�ڎ��Ɏw�肳���<BR>
     */
    public String delivaryMonth;

    /**
     * @@roseuid 40F7AE0C007D
     */
    public WEB3FuturesOpenMarginInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00263<BR>
     * �@@�P�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h2�F�����h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * �Q�j�����ݒ�`�F�b�N<BR>
     *�@@�Q�|�P�j�S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B<BR>
     *�@@�@@�@@�E�����R�[�h<BR>
     *�@@�@@�@@�E����s��<BR>
     *�@@�@@�@@�E�w�����<BR>
     *�@@�@@�@@�E����<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00830<BR>
     *�@@�Q�|�Q�j�����I������<BR>
     *�@@�@@����s��,�w�����,������<BR>
     *  �@@�����ꂩ�̍��ڂ��ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00830<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A218890278
     */
    public void validate() throws WEB3BaseException
    {
        // (�P�j���敪�`�F�b�N
        //�P�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263, 
                this.getClass().getName() + "validate",
                "���敪��null�ł���");
        }
        //�P�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType) 
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264, 
                this.getClass().getName() + "validate",
                "���敪���h1�F�����h�A�h2�F�����h�ȊO�ł���");
        }
        
        // �Q�j�����ݒ�`�F�b�N         
        // �Q�|�P�j�S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B           
        if((futProductCode!=null)          
            &&(marketCode!=null)           
            &&(targetProductCode!=null)            
            &&(delivaryMonth!=null))           
        {          
            throw new WEB3BusinessLayerException(      
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,     
                this.getClass().getName() + "validate",    
                "���̓p�����[�^�`�F�b�N�G���[�B");  
        }          
            
        // �Q�|�Q�j�����I�����Ɏ���s��,�w�����,�����̂����ꂩ�̍��ڂ�         
        // �ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B         
        if((marketCode==null)
            &&(targetProductCode==null)
            &&(delivaryMonth==null))           
        {          
            return;        
        }          
        else           
        {          
            if((marketCode==null)
                ||(targetProductCode==null)
                ||(delivaryMonth==null))       
            {      
                throw new WEB3BusinessLayerException(  
                    WEB3ErrorCatalog.BUSINESS_ERROR_00830, 
                    this.getClass().getName() + "validate",
                    "���̓p�����[�^�`�F�b�N�G���[�B");
            }      
        }          
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE0C009C
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOpenMarginInputResponse(this);
    }
}
@
