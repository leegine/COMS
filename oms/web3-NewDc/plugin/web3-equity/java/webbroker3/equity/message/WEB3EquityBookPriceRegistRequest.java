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
filename	WEB3EquityBookPriceRegistRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������뉿�P���o�^���N�G�X�g(WEB3EquityBookPriceRegistRequest.java)
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
import webbroker3.util.WEB3StringTypeUtility;


/**
 * �i���������뉿�P���o�^���N�G�X�g�j�B<BR>
 * <BR>
 * ���������뉿�P���o�^���N�G�X�g�N���X<BR>
 */
public class WEB3EquityBookPriceRegistRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityBookPriceRegistRequest.class);
        
    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200502141000L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_book_price_regist";
    
    /**
     * (�ۗL���YID)<BR>
     * <BR>
     * �ۗL���YID<BR>
     */
    public String assetId;
    
    /**
     * (�ύX��T�Z�뉿�P��)<BR>
     * <BR>
     * �ύX��T�Z�뉿�P��<BR>
     */
    public String aftBookPrice;
    
    /**
     * @@roseuid 4206CF2F01D1<BR>
     */
    public WEB3EquityBookPriceRegistRequest() 
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
     * �Q�j�ύX��T�Z�뉿�P���`�F�b�N<BR>
     * �@@�Q�|�P�j�ύX��T�Z�뉿�P����null�̏ꍇ�A<BR>
     * �@@�@@�u�ύX��T�Z�뉿�P���������́v�̗�O���X���[����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�ύX��T�Z�뉿�P�����ȉ��̏����ɊY������ꍇ�A<BR>
     * �@@�@@�u�ύX��T�Z�뉿�P�����s���Ȓl�v�̗�O���X���[����B<BR>
     * �@@�@@�@@�E�ύX��T�Z�뉿�P�� != ���l<BR>
     * �@@�@@�@@�E�ύX��T�Z�뉿�P�� <= 0<BR>
     * �@@�@@�@@�E�ύX��T�Z�뉿�P���̌��� > 8��<BR>
     * @@throws WEB3BaseException<BR>
     * @@roseuid 41B65CD401C7<BR>
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
        
        // �ύX��뉿�P���`�F�b�N
        if (this.aftBookPrice == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01920,
                STR_METHOD_NAME,
                "�ύX��뉿�P���������͂ł��B");
        }
        
        // ���l�`�F�b�N
        if (WEB3StringTypeUtility.isNumber(this.aftBookPrice))
        {
            // �����`�F�b�N
            if (this.aftBookPrice.length() > 8)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01920,
                    STR_METHOD_NAME,
                    "�ύX��뉿�P����8���𒴂���l�ł��B");
            }
            
            // 0�ȉ��`�F�b�N
            double l_dblBookValuePrice = Double.parseDouble(this.aftBookPrice);
            if (l_dblBookValuePrice < 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01920,
                    STR_METHOD_NAME,
                    "�ύX��뉿�P����0�����̒l�ł��B");
            }
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01920,
                STR_METHOD_NAME,
                "�ύX��뉿�P�������l�ȊO�̒l�ł��B");
        }
    }
    
    public WEB3GenResponse createResponse()
    {
        return new WEB3EquityBookPriceRegistResponse(this);
    }
}
@
