head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleReasonUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ϗ��R���(WEB3AdminForcedSettleReasonUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/24 ��іQ (���u) �V�K�쐬
Revesion History : 2007/07/24 �����q (���u) �V�K�쐬�@@�d�l�ύX���f��No.159
Revesion History : 2008/01/17 �И��� (���u) �d�l�ύX���f��No.181
*/

package webbroker3.eqtypeadmin.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�������ϗ��R���)<BR>
 * �������ϗ��R���N���X<BR>
 * <BR>
 * ���������ς��s����ƂȂ�ݒ���<BR>
 * <BR>
 * @@author ��іQ
 * @@version 1.0
 */

public class WEB3AdminForcedSettleReasonUnit extends Message
{
    /**
     * (�������ϗ��R)<BR>
     * �������ϗ��R<BR>
     * <BR>
     * 0�F�@@���ϊ�������<BR>
     * 1�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�y�x�j<BR>
     * 2�F�@@�ۏ؋��ێ������i�I�����C���J�n�O�E�d�x�j<BR>
     * 3�F�@@�ۏ؋��ێ������i��ԁj<BR>
     * 4�F  �ۏ؋��ێ������i�I�����C���J�n�O�E�@@��j<BR>
     * 9�F�@@�蓮��������<BR>
     */
    public String forcedSettleReason;
    
    /**
     * (�ۏ؋��ێ���)<BR>
     * �ۏ؋��ێ���<BR>
     */
    public String marginMaintenanceRate;
    
    /**
     * (�Ǐ،o�ߓ������)<BR>
     * �Ǐ،o�ߓ������<BR>
     */
    public String additionalElapsedDaysUpperLimit;
    
    /**
     * @@roseuid 462CA42801BA
     */
    public WEB3AdminForcedSettleReasonUnit()
    {
     
    }
}
@
