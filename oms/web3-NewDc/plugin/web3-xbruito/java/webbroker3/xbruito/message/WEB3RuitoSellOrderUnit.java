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
filename	WEB3RuitoSellOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��񒍕����׃N���X(WEB3RuitoSellOrderUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 ���E (���u) �V�K�쐬
*/
package webbroker3.xbruito.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * ��񒍕�����<BR>
 */
public class WEB3RuitoSellOrderUnit extends Message
{

    /**
     * ��������<BR>
     */
    public Date orderDate;

    /**
     *  1:��t��(�V�K����)�@@       3:������(�V�K����)<BR>�@@�@@�@@ 
     *  6:�������s(�V�K����)     12:��t��(�������)<BR>
     * 14:������(�������)�@@�@@    15:�������s(�������)<BR>
     * 30:MRF����G���[�@@�@@�@@      31:��t��(MRF���L��)<BR>
     * 32:������(MRF���L��)
     */
    public String orderState;

    /**
     * �������ʋ敪<BR>
     * <BR>
     * 3: ���z�@@4:����<BR>
     */
    public String ruitoOrderQuantityType;

    /**
     * ��������<BR>
     */
    public String ruitoOrderQuantity;

    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40922B610196
     */
    public WEB3RuitoSellOrderUnit()
    {

    }
}
@
