head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOpenMarginInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����V�K���������̓��N�G�X�g(WEB3MarginOpenMarginInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/17 ������ (���u) �V�K�쐬
                   2006/12/25 �����F (���u) ���f�� 1086
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.equity.define.WEB3MarginTradeTypeDef;

import webbroker3.util.WEB3LogUtility;

/**
 * �i�M�p����V�K���������̓��N�G�X�g�j�B<br>
 * <br>
 * �M�p����V�K���������̓��N�G�X�g�N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3MarginOpenMarginInputRequest extends WEB3GenRequest 
{
    /**
     * (���O�o�̓��[�e�B���e�B�B)
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MarginOpenMarginInputRequest.class);
    
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_openMarginInput";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * (�����R�[�h)<BR>
     * <BR>
     * �����w��̏ꍇ�Ɏg�p�B<BR>
     */
    public String productCode;
    
    /**
     * (�s��R�[�h)<BR>
     * <BR>
     * �����w��̏ꍇ�Ɏg�p�B<BR>
     */
    public String marketCode;
    
    /**
     * (����敪)<BR>
     * 3�F�V�K���������@@4�F�V�K�������� �i�������Anull�l��������j<BR>
     * <BR>
     * null�l�̏ꍇ�A�����^�������m�肵�Ă��Ȃ��ꍇ���Ӗ�����<BR>
     * (OrderTypeEnum�ɂĒ�`)<BR>
     */
    public String tradingType;
    
    /**
     * @@roseuid 4140477E0074
     */
    public WEB3MarginOpenMarginInputRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�s��w��`�F�b�N<BR>
     * �@@this.�s��R�[�h��null�ł��A���L�ȊO�̒l�̏ꍇ�A<BR>
     * �@@�u�s��R�[�h������`�̒l�v�̗�O���X���[����B<BR>
     *          �E�h1�F�����h<BR>
     *          �E�h2�F���h <BR>
     *          �E�h3�F���É��h <BR>
     *          �E�h6�F�����h <BR>
     *          �E�h8�F�D�y�h <BR>
     *          �E�h9�FNNM�h <BR>
     *          �E�h10�FJASDAQ�h<BR>
     *          �E�h99�F�D��s��h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00608<BR>
     * <BR>
     * �Q�j�@@����敪�`�F�b�N<BR>
     * �@@�Q�|�P�jthis.����敪��null�ł��A���L�ȊO�̒l�̏ꍇ�A<BR>
     * �@@�@@�@@�@@�u����敪������`�̒l�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h3�F�V�K���������h<BR>
     * �@@�@@�@@�@@�E�h4�F�V�K���������h<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:   BUSINESS_ERROR_00602<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 407E5C560167
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "WEB3MarginOpenMarginInputRequest: validate()";
        log.entering(STR_METHOD_NAME);
        
        log.debug("�s��w��`�F�b�N");
        // �P�j�@@�s��w��`�F�b�N
        if (this.marketCode != null
                && !WEB3MarketCodeDef.TOKYO.equals(this.marketCode)
                && !WEB3MarketCodeDef.OSAKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.NAGOYA.equals(this.marketCode)
                && !WEB3MarketCodeDef.FUKUOKA.equals(this.marketCode)
                && !WEB3MarketCodeDef.SAPPORO.equals(this.marketCode)
                && !WEB3MarketCodeDef.NNM.equals(this.marketCode)
                && !WEB3MarketCodeDef.JASDAQ.equals(this.marketCode)
                && !WEB3MarketCodeDef.PRIORITY_MARKET.equals(this.marketCode))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00608,
            this.getClass().getName() + "validate");
        }

        log.debug("����敪�`�F�b�N");
        // �Q�j�@@����敪�`�F�b�N
        if (this.tradingType != null
                && !WEB3MarginTradeTypeDef.OPEN_LONG_MARGIN.equals(this.tradingType)
                && !WEB3MarginTradeTypeDef.OPEN_SHORT_MARGIN.equals(this.tradingType))
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00602,
            this.getClass().getName() + "validate");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4140477E0088
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MarginOpenMarginInputResponse(this);
    }
}
@
