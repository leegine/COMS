head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecutionEndUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������o���I������(WEB3FeqExecutionEndUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�O�������o���I������)<BR>
 * �O�������o���I�����׃N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqExecutionEndUnit extends Message 
{
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (�s�ꖼ)<BR>
     * �s�ꖼ
     */
    public String marketName;
    
    /**
     * (�����敪)<BR>
     * �����敪<BR>
     * <BR>
     * 0�F�@@������<BR>
     * 1�F�@@������<BR>
     * 9�F�@@�G���[
     */
    public String executionEndDiv;
    
    /**
     * (�O�񔭒���)<BR>
     * �O�񔭒���
     */
    public Date previousOrderBizDate;
    
    /**
     * (�O��o���I������)<BR>
     * �O��o���I������
     */
    public Date previousExecutionEndTime;
    
    /**
     * (�O�������o���I������)<BR>
     * �R���X�g���N�^
     * @@roseuid 4202079102E4
     */
    public WEB3FeqExecutionEndUnit() 
    {
     
    }
}
@
