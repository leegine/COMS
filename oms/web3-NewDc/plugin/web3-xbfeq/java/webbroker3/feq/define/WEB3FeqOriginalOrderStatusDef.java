head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOriginalOrderStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������敪�萔��`�C���^�t�F�C�X(WEB3FeqOriginalOrderStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/19 �Г� (���u) �V�K�쐬
*/
package webbroker3.feq.define;

/**
 * �����������敪�萔��`�C���^�t�F�C�X
 *
 * @@author �Г�
 * @@version 1.0
 */
public interface WEB3FeqOriginalOrderStatusDef
{
    /**
     * 0�F�����
     */
    public static final String CANCELING = "0";
    
    /**
     * 1�F�����
     */
    public static final String CANCELED = "1";

    /**
     * 2�F����G���[
     */
    public static final String CANCEL_ERROR = "2";
}
@
