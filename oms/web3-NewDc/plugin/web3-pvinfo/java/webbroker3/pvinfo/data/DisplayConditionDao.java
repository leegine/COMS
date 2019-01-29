head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.11.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	DisplayConditionDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.pvinfo.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.pvinfo.data.*;
import com.fitechlabs.dbind.*;

/** 
 * {@@link DisplayConditionDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link DisplayConditionRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see DisplayConditionPK 
 * @@see DisplayConditionRow 
 */
public class DisplayConditionDao extends DataAccessObject {


  /** 
   * ����{@@link DisplayConditionDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private DisplayConditionRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link DisplayConditionRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link DisplayConditionDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link DisplayConditionDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link DisplayConditionRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof DisplayConditionRow )
                return new DisplayConditionDao( (DisplayConditionRow) row );
            throw new java.lang.IllegalArgumentException( "Not a DisplayConditionRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link DisplayConditionRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link DisplayConditionRow}�I�u�W�F�N�g 
    */
    protected DisplayConditionDao( DisplayConditionRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link DisplayConditionRow}�I�u�W�F�N�g���擾���܂��B
   */
    public DisplayConditionRow getDisplayConditionRow() {
        return row;
    }


  /** 
   * �w���{@@link DisplayConditionRow}�I�u�W�F�N�g����{@@link DisplayConditionDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link DisplayConditionRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link DisplayConditionDao}�擾�̂��߂Ɏw���{@@link DisplayConditionRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link DisplayConditionDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static DisplayConditionDao forRow( DisplayConditionRow row ) throws java.lang.IllegalArgumentException {
        return (DisplayConditionDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link DisplayConditionRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link DisplayConditionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link DisplayConditionPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link DisplayConditionParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( DisplayConditionRow.TYPE );
    }


  /** 
   * {@@link DisplayConditionRow}����ӂɓ��肷��{@@link DisplayConditionPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link DisplayConditionRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link DisplayConditionParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link DisplayConditionPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static DisplayConditionPK newPkObject() throws DataNetworkException, DataQueryException {
        long id = newPkValue();
        return new DisplayConditionPK( id );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link DisplayConditionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_displayConditionId �����Ώۂł���p_displayConditionId�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DisplayConditionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DisplayConditionRow findRowByPk( long p_displayConditionId ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayConditionPK pk = new DisplayConditionPK( p_displayConditionId );
        return findRowByPk( pk );
    }


  /** 
   * �w���DisplayConditionPK�I�u�W�F�N�g����{@@link DisplayConditionRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����DisplayConditionPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link DisplayConditionRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static DisplayConditionRow findRowByPk( DisplayConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (DisplayConditionRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(long)}�����{@@link #forRow(DisplayConditionRow)}���g�p���Ă��������B 
   */
    public static DisplayConditionDao findDaoByPk( long p_displayConditionId ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayConditionPK pk = new DisplayConditionPK( p_displayConditionId );
        DisplayConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(DisplayConditionPK)}�����{@@link #forRow(DisplayConditionRow)}���g�p���Ă��������B 
   */
    public static DisplayConditionDao findDaoByPk( DisplayConditionPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        DisplayConditionRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_displayConditionId, and �ɂĎw��̒l�����ӂ�{@@link DisplayConditionRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_displayConditionId �����Ώۂł���p_displayConditionId�t�B�[���h�̒l
   * 
   * @@return �����w���p_displayConditionId, and �̒l�ƈ�v����{@@link DisplayConditionRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static DisplayConditionRow findRowByDisplayConditionId( long p_displayConditionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            DisplayConditionRow.TYPE,
            "display_condition_id=?",
            null,
            new Object[] { new Long(p_displayConditionId) } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (DisplayConditionRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query DisplayConditionDao.findRowsByDisplayConditionId(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByDisplayConditionId(long)}�����{@@link #forRow(DisplayConditionRow)}���g�p���Ă��������B 
   */
    public static DisplayConditionDao findDaoByDisplayConditionId( long p_displayConditionId ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByDisplayConditionId( p_displayConditionId ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_conditionNo, and �ɂĎw��̒l�Ɉ�v����{@@link DisplayConditionRow}�I�u�W�F�N�g��{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_conditionNo �����Ώۂł���p_conditionNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_conditionNo, and �̒l�ƈ�v����{@@link DisplayConditionRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeConditionNo( String p_institutionCode, String p_conditionNo ) throws DataNetworkException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            DisplayConditionRow.TYPE,
            "institution_code=? and condition_no=?",
            null,
            new Object[] { p_institutionCode, p_conditionNo } );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeConditionNo(String, String)}�����{@@link #forRow(DisplayConditionRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeConditionNo( String p_institutionCode, String p_conditionNo ) throws DataNetworkException, DataQueryException  {
        return forRows( findRowsByInstitutionCodeConditionNo( p_institutionCode, p_conditionNo ) );
    }

}
@
