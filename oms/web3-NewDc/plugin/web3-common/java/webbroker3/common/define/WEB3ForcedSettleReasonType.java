head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.51.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ForcedSettleReasonType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ϗ��R�敪�萔��`�C���^�t�F�C�X(WEB3ForcedSettleReasonType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/25 �h�C(���u) �V�K�쐬
Revision History : 2008/01/23 �h�C (���u) �����d�l�ύX�Ǘ��䒠(�c�a���C�A�E�g)No.155��Ή�
*/
package webbroker3.common.define;

/**
 * �������ϗ��R�敪 �萔��`�C���^�t�F�C�X
 *
 * @@author �h�C(���u)
 * @@version 1.0
 */
public interface WEB3ForcedSettleReasonType
{
    /**
     * 0�F��������
     */
    public final static String FIXED_DATE_COMING = "0";

    /**
     * 1�F�ۏ؋��ێ�������i�I�����C���J�n�O�E�y�x�j
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SLIGHTNESS = "1";

    /**
     * 2�F�ۏ؋��ێ�������i�I�����C���J�n�O�E�d�x�j
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_SERIOUSNESS = "2";

    /**
     * 3�F�ۏ؋��ێ�������i��ԁj
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK_MARKET = "3";

    /**
     * 4�F�ۏ؋��ێ�������i�I�����C���J�n�O�E�@@��j
     */
    public final static String GUARANTEE_MAINTENANCE_BREAK_BEFORE_ONLINE_LEGAL = "4";

    /**
     * 9�F�蓮��������
     */
    public final static String MANUAL_FORCED_SETTLE = "9";
}
@
