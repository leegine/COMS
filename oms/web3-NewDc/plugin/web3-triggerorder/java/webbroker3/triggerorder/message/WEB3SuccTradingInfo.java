head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.41.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccTradingInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A������������(WEB3SuccTradingInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�A������������)<BR>
 * �A������������<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3SuccTradingInfo extends Message 
{
    
    /**
     * (���i�敪)<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     */
    public String commodityType;
    
    /**
     * (�A����������敪�ꗗ)<BR>
     * �A����������敪�ꗗ<BR>
     * <BR>
     * 01�F�@@���t�i�O�񒍕��j<BR>
     * 02�F�@@���t<BR>
     * 03�F�@@���t�i�O�񒍕��j<BR>
     * 04�F�@@���t�i�����c�j<BR>
     * 05�F�@@�M�p�V�K���i�O�񒍕��j<BR>
     * 06�F�@@�M�p�V�K��<BR>
     * 07�F�@@�M�p�ԍρi�O�񒍕��j<BR>
     * 08�F�@@�M�p�ԍρi�����c�j<BR>
     * 09�F�@@�������n�i�O�񒍕��j<BR>
     * 10�F�@@�������n�i�����c�j<BR>
     * 11�F�@@�敨�V�K���i�O�񒍕��j<BR>
     * 12�F�@@�敨�V�K��<BR>
     * 13�F�@@�敨�ԍρi�O�񒍕��j<BR>
     * 14�F�@@�敨�ԍρi�����c�j<BR>
     * 15�F�@@OP�V�K���i�O�񒍕��j<BR>
     * 16�F�@@OP�V�K��<BR>
     * 17�F�@@OP�ԍρi�O�񒍕��j<BR>
     * 18�F�@@OP�ԍρi�����c�j<BR>
     */
    public String[] succTradingTypeList;
    
    /**
     * (�A������������)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 431F849400FE
     */
    public WEB3SuccTradingInfo() 
    {
     
    }
}
@
