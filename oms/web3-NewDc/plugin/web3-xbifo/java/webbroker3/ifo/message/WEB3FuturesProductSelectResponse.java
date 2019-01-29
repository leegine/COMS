head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K�����������I����ʃ��N�G�X�g�N���X(WEB3FuturesProductSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ (���u) �V�K�쐬
                 : 2006/08/18 �s�p (���u) ���f��No.536
*/
package webbroker3.ifo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����w���敨�V�K�����������I����ʃ��X�|���X)<BR>
 * �����w���敨�V�K�����������I����ʃ��X�|���X�N���X<BR>
 * 
 * @@author ������
 * @@version 1.0 
 */
public class WEB3FuturesProductSelectResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_ProductSelect";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201858L;
    
    /**
     * (�V�K���\�z)<BR>
     */
    public String futTradingPower;
    
    /**
     * (����s��ꗗ)<BR>
     * 1�F�����@@2�F���@@3�F���É�<BR>
     */
    public String[] marketList;
    
    /**
     * (�w����ʈꗗ)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225 <BR>
     */
    public String[] targetProductList;
    
    /**
     * (�����ꗗ)<BR>
     * �e�l��YYYYMM�`��<BR>
     */
    public String[] delivaryMonthList;
    
    /**
     * (����I���x������)<BR>
     * �Ǌԋ߂̎s�ꂪ����΁A���̃R�[�h���i�[<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 40F7AE0C0177
     */
    public WEB3FuturesProductSelectResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
