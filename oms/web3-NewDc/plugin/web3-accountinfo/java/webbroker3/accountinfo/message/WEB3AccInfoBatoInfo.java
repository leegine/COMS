head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoBatoInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �d�q����񃁃b�Z�[�W(WEB3AccInfoBatoInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/12 ���C�g (���u) �V�K�쐬
*/

package webbroker3.accountinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�d�q�����)<BR>
 * �d�q����񃁃b�Z�[�W<BR>
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AccInfoBatoInfo extends Message 
{
    
    /**
     * (����񍐏���t��ԋ敪)<BR>
     * ����񍐏���t��ԋ敪<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�d�q�z�z<BR>
     */
    public String tradingReportStateDiv;
    
    /**
     * (����c���񍐏���t��ԋ敪)<BR>
     * ����c���񍐏���t��ԋ敪<BR>
     * <BR>
     * 0�F�@@�X�֔z�z<BR>
     * 1�F�@@�X�֔z�z�i��n�s�x�쐬�j<BR>
     * 9�F�@@�d�q�z�z<BR>
     */
    public String positionReportStateDiv;
    
    /**
     * (����c���񍐏��쐬�����敪)<BR>
     * ����c���񍐏��쐬�����敪<BR>
     * <BR>
     * 1�F�@@����<BR>
     * 3�F�@@3����<BR>
     */
    public String positionReportCycleDiv;
    
    /**
     * (����c���񍐏��a��؍쐬��ԋ敪)<BR>
     * ����c���񍐏��a��؍쐬��ԋ敪<BR>
     * <BR>
     * 0�F�@@�s�쐬<BR>
     * 1�F�@@�쐬<BR>
     * <BR>
     */
    public String certificateDepositStateDiv;
    
    /**
     * (����c���񍐏��v�Z���쐬��ԋ敪)<BR>
     * ����c���񍐏��v�Z���쐬��ԋ敪<BR>
     * <BR>
     * 0�F�@@�s�쐬<BR>
     * 1�F�@@�쐬<BR>
     */
    public String accountStatementStateDiv;
    
    /**
     * @@roseuid 418F39EE0271
     */
    public WEB3AccInfoBatoInfo() 
    {
     
    }
}
@
