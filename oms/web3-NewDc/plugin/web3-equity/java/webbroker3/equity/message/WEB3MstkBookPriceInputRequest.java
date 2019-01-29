head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBookPriceInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����뉿�P���o�^���̓��N�G�X�g(WEB3MstkBookPriceInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/14 �򑺁@@�m�m(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * �i�����~�j�����뉿�P���o�^���̓��N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j�����뉿�P���o�^���̓��N�G�X�g�N���X<BR>
 */
public class WEB3MstkBookPriceInputRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBookPriceInputRequest.class);
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mstk_book_price_input";
    
    /**
     * (�ۗL���YID)<BR>
     * <BR>
     * �ۗL���YID<BR>
     */
    public String assetId;
    
    /**
     * @@roseuid 4206CF450154<BR>
     */
    public WEB3MstkBookPriceInputRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�ۗL���YID�`�F�b�N<BR>
     * �@@�P�|�P�j�ۗL���YID��null�̏ꍇ�A<BR>
     * �@@�@@�u�ۗL���YID�������́v�̗�O���X���[����B<BR>
     * <BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41C10FB70246<BR>
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        // �ۗL���YID�̃`�F�b�N
        if (this.assetId == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01919,
                STR_METHOD_NAME,
                "�ۗL���YID�������͂ł��B");
        }
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3MstkBookPriceInputResponse(this);
    }
}
@
