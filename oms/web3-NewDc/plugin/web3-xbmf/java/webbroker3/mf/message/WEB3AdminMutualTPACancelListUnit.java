head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.09.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminMutualTPACancelListUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�Ǘ��җ]�͒�������ꗗ�s(WEB3AdminMutualTPACancelListUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/19 ���� (���u) �V�K�쐬
*/

package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 *(���M�Ǘ��җ]�͒�������ꗗ�s)<BR>
 *�����M���Ǘ��җ]�͒�������ꗗ�s�f�[�^�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0   
 */

public class WEB3AdminMutualTPACancelListUnit extends Message
{
    /**
     * (����ID)<BR>
     *  ����ID<BR>
     */
    public String orderId;
    
    /**
     * (�����R�[�h)<BR>
     *  �����R�[�h<BR>
     */
    public String mutualProductCode;
    
    /**
     * (������)<BR>
     *  ������<BR>
     */
    public String mutualProductName;
    
    /**
     * (���Z���z)<BR>
     *  ���Z���z<BR>
     */
    public String settlePrice;
    
    /**
     * (������)<BR>
     *  ������<BR>
     */
    public Date orderBizDate;
    
    /**
     * (����)<BR>
     *  ����<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (��n��)<BR>
     *  ��n��<BR>
     */
    public Date deliveryDate;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 4073BCCD0389<BR>
     */
    public WEB3AdminMutualTPACancelListUnit()
    {
        
    }
}
@
