head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenInterestDealingInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����̂��������(WEB3AccOpenInterestDealingInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 �A�C��(���u) �V�K�쐬
*/

package webbroker3.accountopen.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����̂��������)<BR>
 * �����̂��������<BR>
 *   
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AccOpenInterestDealingInfo extends Message 
{
    
    /**
     * (���������t���O)<BR>
     * ���������t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�����Ȃ�<BR>
     */
    public boolean interestEquityFlag;
    
    /**
     * (�����~�j�����t���O)<BR>
     * �����~�j�����t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�����Ȃ�<BR>
     */
    public boolean interestMstkFlag;
    
    /**
     * (�M�p����t���O)<BR>
     * �M�p����t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�����Ȃ�<BR>
     */
    public boolean interestMarginFlag;
    
    /**
     * (���t���O)<BR>
     * ���t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�����Ȃ�<BR>
     */
    public boolean interestBondFlag;
    
    /**
     * (�����M���t���O)<BR>
     * �����M���t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�����Ȃ�<BR>
     */
    public boolean interestFundFlag;
    
    /**
     * (�敨�E�I�v�V�����t���O)<BR>
     * �敨�E�I�v�V�����t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�����Ȃ�<BR>
     */
    public boolean interestFoFlag;
    
    /**
     * (�O���،��t���O)<BR>
     * �O���،��t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�����Ȃ�<BR>
     */
    public boolean interestFEquityFlag;
    
    /**
     * (���̑��t���O)<BR>
     * ���̑��t���O<BR>
     * <BR>
     * true�F�@@��������<BR>
     * false�F�@@�����Ȃ�<BR>
     */
    public boolean interestEtcFlag;
    
    /**
     * @@roseuid 41B45E7A007D
     */
    public WEB3AccOpenInterestDealingInfo() 
    {
     
    }
}
@
