head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTradingPowerUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���t�\�z�i�O�݁j(WEB3FeqTradingPowerUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���t�\�z�i�O�݁j)<BR>
 * ���t�\�z�i�O�݁j�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqTradingPowerUnit extends Message 
{
    
    /**
     * (���t�\�z)<BR>
     * ���t�\�z<BR>
     */
    public String tradingPower;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (���t�\�z�i�O�݁j)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 4282BFE903B6
     */
    public WEB3FeqTradingPowerUnit() 
    {
     
    }
}
@
