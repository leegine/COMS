head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.03.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyTotalUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t���z���v(WEB3MutualFixedBuyTotalUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 ���G�� (���u) �V�K�쐬
Revesion History : 2008/07/08 ���u�� (���u) �d�l�ύX ���f��No.604
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���M�莞��z���t���z���v)<BR>
 * ���M�莞��z���t���z���v<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyTotalUnit extends Message
{
    /**
     * (���X���v)<BR>
     * ���X���v<BR>
     */
    public String monthlyBATotal;
    
    /**
     * (�ςݑ������v)<BR>
     * �ςݑ������v<BR>
     */
    public String increaseBATotal;
    
    /**
     * (���������N��)<BR>
     * ���������N��<BR>
     */
    public Date debitAccountYM;

    /**
     * (�m��������z���v�i�ςݑ����j)<BR>
     * �m��������z���v�i�ςݑ����j<BR>
     */
    public  String definiteIncreaseBATotal;

    /**
     * (���M�莞��z���t���z���v�̃C���X�^���X�𐶐�����B)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyTotalUnit()
    {
    }
}
@
