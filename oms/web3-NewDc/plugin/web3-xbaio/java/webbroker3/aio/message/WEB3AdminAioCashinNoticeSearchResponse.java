head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�������X�|���X(WEB3AdminAioCashinNoticeSearchResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 ��O��  (���u) �V�K�쐬
                 : 2006/8/23 �Ԑi(���u) �d�l�ύX ���f�� 614
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �����ʒm�������X�|���X<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeSearchResponse extends WEB3GenResponse
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_aio_cashin_notice_search";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211117L;    
    
    /**
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * ���햾�׌���<BR>
     */
    public String normalNumber = "0";
    
    /**
     * �G���[���׌���<BR>
     */
    public String errorNumber = "0";
    
    /**
     * ���������׌���<BR>
     * <BR>
     */
    public String nonTransactionNumber = "0";
    
    /**
     * �����׌���<BR>
     */
    public String totalNumber = "0";
    
    /**
     * ��������<BR>
     */			
    public String cashinNumber = "0";
    
    /**
     * �o������<BR>
     */			
    public String cashoutNumber = "0";

    /**
     * ���햾�׍��v���z<BR>
     */
    public String normalTotalPrice = "0";

    /**
     * �G���[���׍��v���z<BR>
     */
    public String errorTotalPrice = "0";
    
    /**
     * ���������׍��v���z<BR>
     */
    public String nonTransactionTotalPrice = "0";
    
    /**
     * �����׍��v���z<BR>
     */
    public String totalPrice = "0";
    
    /**
     * �������v���z<BR>
     */		
    public String cashinTotalPrice = "0";
    
    /**
     * �o�����v���z<BR>
     */
    public String cashoutTotalPrice = "0";
    
    /**
     * �I��ʉ݃R�[�h<BR>
     * �\���p�ʉ݃R�[�h<BR>
     */
    public String[] selectCurrencyCode;
    
    /**
     * �����ʒm���׈ꗗ<BR>
     */
    public WEB3AioCashinNoticeUnit2[] cashinNoticeList;
    
    /**
     * �O�݃T�}�����ꗗ<BR>
     */
    public WEB3ForeignSummaryInfo[] foreignSummaryList;
    
    /**
     * (�����ʒm�������X�|���X)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40DF7B7B01AC
     */
    public WEB3AdminAioCashinNoticeSearchResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminAioCashinNoticeSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
