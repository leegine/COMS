head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsOpenMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����V�K���������͉�ʃ��N�G�X�g(WEB3OptionsOpenMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ���o�� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�����w���I�v�V�����V�K���������͉�ʃ��N�G�X�g)<BR>
 * �����w���I�v�V�����V�K���������͉�ʃ��N�G�X�g�N���X<BR>
 * @@author ���o��
 * @@version 1.0
 */
public class WEB3OptionsOpenMarginInputRequest extends WEB3GenRequest
{

    /**
      * Logger
      */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsOpenMarginInputRequest.class);
        
    /**
    * SerialVersionUID<BR>
    */
    public static final long serialVersionUID = 200406141510L;

    /**
    * PTYPE<BR>
    */
    public static final String PTYPE = "options_openMarginInput";

    /**
     * �����R�[�h<BR>
     * �X�����O�V���b�g�N�����A�����w��̏ꍇ<BR>
     */
    public String opProductCode;

    /**
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;

    /**
     * ����s��<BR>
     * 1�F�����@@2�F���<BR>
     * <BR>
     * �����I����ʂ��J�ڎ��Ɏw�肳���<BR>
     */
    public String marketCode;

    /**
     * �w�����<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     * <BR>
     * �����I����ʂ��J�ڎ��Ɏw�肳���<BR>
     */
    public String targetProductCode;

    /**
     * ����<BR>
     * YYYYMM�`��<BR>
     * <BR>
     * �����I����ʂ��J�ڎ��Ɏw�肳���<BR>
     */
    public String delivaryMonth;

    /**
     * �I�v�V�������i�敪<BR>
     * P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����<BR>
     * <BR>
     * �����I����ʂ��J�ڎ��Ɏw�肳���<BR>
     */
    public String opProductType;

    /**
     * �s�g���i<BR>
     * �����I����ʂ��J�ڎ��Ɏw�肳���<BR>
     */
    public String strikePrice;

    /**
    * @@roseuid 40C0A8EE008C
    */
    public WEB3OptionsOpenMarginInputRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00263<BR>
     * �@@�P�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h2�F�����h<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00264<BR>
     * <BR>
     * �Q�j�����ݒ�`�F�b�N<BR>
     *�@@�Q�|�P�j�S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B<BR>
     *�@@�@@�@@�E�����R�[�h<BR>
     *�@@�@@�@@�E����s��<BR>
     *�@@�@@�@@�E�w�����<BR>
     *�@@�@@�@@�E����<BR>
     *�@@�@@�@@�E�I�v�V�������i�敪<BR>
     *�@@�@@�@@�E�s�g���i<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00830<BR>
     *�@@�Q�|�Q�j�����I������<BR>
     *�@@�@@����s��,�w�����,����,�I�v�V�������i�敪,�s�g���i��<BR>
     *�@@  �����ꂩ�̍��ڂ��ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00830<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4068CFAD0281
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //���敪�`�F�b�N
        //this.���敪��null�̒l�ł���Η�O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.contractType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263,
                getClass().getName() + "validate",
                "���敪��null�ł���");
        }

        //���敪�`�F�b�N
        //this.���敪���h1�F�����h�h2�F����"�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType)
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264,
                getClass().getName() + "validate",
                "���敪���h1�F�����h�A�h2�F�����h�ȊO�ł���");
        }

        //�i�Q�j�����ݒ�`�F�b�N             
        // �Q�|�P�j�S�Ẵ��N�G�X�g���ڂ�ݒ肵�Ă���ꍇ�A��O���X���[����B               
        if((opProductCode!=null)               
            &&(marketCode!=null)               
            &&(targetProductCode!=null)                
            &&(delivaryMonth!=null)                
            &&(opProductType!=null)                
            &&(strikePrice!=null))             
        {              
            throw new WEB3BusinessLayerException(         
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
                this.getClass().getName() + "validate",        
                "���̓p�����[�^�`�F�b�N�G���[�B");      
        }              
                
        // �Q�|�Q�j�����I�����Ɏ���s��,�w�����,����,�I�v�V�������i�敪,�s�g���i�̂����ꂩ�̍��ڂ�              
        // �ݒ肳��Ă��Ȃ��ꍇ�A��O���X���[����B             
        if((marketCode==null)
            &&(targetProductCode==null)
            &&(delivaryMonth==null)                
            &&(opProductType==null)
            &&(strikePrice==null))          
        {              
            return;            
        }else              
        {              
            if((marketCode==null)
                ||(targetProductCode==null)
                ||(delivaryMonth==null)            
                ||(opProductType==null)
                ||(strikePrice==null))      
            {          
                    throw new WEB3BusinessLayerException(  
                        WEB3ErrorCatalog.BUSINESS_ERROR_00830, 
                        this.getClass().getName() + "validate",
                        "���̓p�����[�^�`�F�b�N�G���[�B");
            }          
        }              
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsOpenMarginInputResponse(this);
    }
}
@
